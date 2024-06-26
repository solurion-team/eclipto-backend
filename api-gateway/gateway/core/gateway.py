import functools
from types import NoneType

import httpx
import ast

from pydantic import TypeAdapter, BaseModel
from http import HTTPMethod
from typing import get_type_hints, get_args
from fastapi import Request, Response, params, HTTPException

from gateway.common.models import ErrorDto

http_client = httpx.AsyncClient(verify=False)


def gate_to(
        method: HTTPMethod,
        service_url: str,
        gateway_path: str,
        ignore_default_headers: bool = False,
        timeout: int = 60
):
    def wrapper(f):
        hints = get_type_hints(f, include_extras=True)
        query_keys = {k for k, v in hints.items() if is_query_meta(v)}
        body_key = next((k for k, v in hints.items() if is_body_meta(v)), None)
        path_keys = {k for k, v in hints.items() if is_path_meta(v)}
        header_keys = {k for k, v in hints.items() if is_header_meta(v)}
        default_header_keys = ["authorization", "cookie"]
        base_url = f"{service_url}{gateway_path}"

        return_class = hints["return"]
        class_adapter = TypeAdapter(return_class)
        error_class_adapter = TypeAdapter(ErrorDto)

        @functools.wraps(f)
        async def inner(request: Request, response: Response, **kwargs):
            url = base_url
            for key in path_keys:
                if key in kwargs:
                    url = url.replace(f"{{{key}}}", str(kwargs[key]))

            query_params = {k: kwargs[k] for k in query_keys if k in kwargs}
            header_params = {k: kwargs[k] for k in header_keys if k in kwargs}

            if not ignore_default_headers:
                header_params.update({k: request.headers[k] for k in default_header_keys if k in request.headers})

            body = None
            if body_key and body_key in kwargs:
                body = kwargs[body_key].dict()

            resp = await http_client.request(
                method=method.upper(),
                url=url,
                headers=header_params,
                params=query_params,
                json=body,
                timeout=timeout
            )
            response.status_code = resp.status_code
            response.body = resp.content
            response.headers.update(resp.headers)

            if response.status_code >= 400:
                response_body = ast.literal_eval(response.body.decode("utf-8"))
                raise HTTPException(status_code=response.status_code, detail=response_body)
            if return_class == NoneType:
                return None

            return class_adapter.validate_json(response.body)

        return inner

    return wrapper


def is_query_meta(value: any):
    annotations = get_args(value)
    return any(isinstance(arg, params.Query) for arg in annotations)


def is_body_meta(value: any):
    annotations = get_args(value)
    return any(isinstance(arg, params.Body) for arg in annotations)


def is_path_meta(value: any):
    annotations = get_args(value)
    return any(isinstance(arg, params.Path) for arg in annotations)


def is_header_meta(value: any):
    annotations = get_args(value)
    return any(isinstance(arg, params.Header) for arg in annotations)
