import json
import uuid
from http import HTTPStatus
from typing import Annotated

import requests
from fastapi import APIRouter, Depends, Body
from pydantic import Field, BaseModel

from config import settings
from gateway.common.models import ErrorDto
from gateway.common.security import bearer_auth_scheme
from gateway.gigachat.models import TaskDescriptionRequest, TaskDescriptionResult
from gateway.core.gateway import http_client

router = APIRouter()
auth_token = "empty"


@router.post(
    "/v1/task/generate-description",
    response_model=TaskDescriptionResult,
    responses={
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="Generate description",
    description="Generate description",
    operation_id="getTaskDescription",
)
async def generate_description(
        token: Annotated[str, Depends(bearer_auth_scheme)],
        task_description_request: Annotated[TaskDescriptionRequest, Body()]
) -> TaskDescriptionResult:
    global auth_token
    payload = {
        "model": "GigaChat",
        "messages": [
            {
                "role": "user",
                "content": f"Напиши описание к задаче на тему {task_description_request.title}. Примерный объем "
                           f"описания - 2-3 предложения"
            }
        ],
        "temperature": 1,
        "top_p": 0.1,
        "n": 1,
        "stream": False,
        "max_tokens": 512,
        "repetition_penalty": 1
    }
    headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': f'Bearer {auth_token}'
    }
    response = await http_client.post(settings.gigachat_competition_url, headers=headers, json=payload)
    if response.status_code == 401:
        auth_token = await auth_gigachat()
        return await generate_description(token, task_description_request)

    return TaskDescriptionResult(description=response.json()["choices"][0]["message"]["content"])


async def auth_gigachat() -> str:
    payload = "scope=GIGACHAT_API_PERS"
    headers = {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json',
        "RqUID": str(uuid.uuid4()),
        'Authorization': f'Bearer {settings.gigachat_auth_token}'
    }
    response = await http_client.request("POST", settings.gigachat_oauth_url, headers=headers, data=payload)
    return response.json()['access_token']
