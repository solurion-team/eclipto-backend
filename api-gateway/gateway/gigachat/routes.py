import json
import uuid
from typing import Annotated

import requests
from fastapi import APIRouter, Depends, Body
from pydantic import Field, BaseModel

from config import settings
from gateway.common.security import bearer_auth_scheme
from gateway.gigachat.models import TaskDescriptionRequest, TaskDescriptionResult

router = APIRouter()
auth_token = ""


@router.post("/v1/task/generate-description")
async def generate_description(
        token: Annotated[str, Depends(bearer_auth_scheme)],
        task_description_request: Annotated[TaskDescriptionRequest, Body()]
) -> TaskDescriptionResult:
    global auth_token
    payload = json.dumps({
        "model": "GigaChat",
        "messages": [
            {
                "role": "user",
                "content": f"Напиши описание к задаче на тему {task_description_request.title}. Примерный объем описания - 2-3 предложения"
            }
        ],
        "temperature": 1,
        "top_p": 0.1,
        "n": 1,
        "stream": False,
        "max_tokens": 512,
        "repetition_penalty": 1
    })
    headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': f'Bearer {auth_token}'
    }
    response = requests.request("POST", settings.gigachat_competition_url, headers=headers, data=payload, verify=False)
    if response.status_code == 401:
        auth_token = auth_gigachat()
        return await generate_description(token, task_description_request)

    return TaskDescriptionResult(description=response.json()["choices"][0]["message"]["content"])


def auth_gigachat() -> str:
    payload = "scope=GIGACHAT_API_PERS"
    headers = {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json',
        "RqUID": str(uuid.uuid4()),
        'Authorization': f'Bearer {settings.gigachat_auth_token}'
    }
    response = requests.request("POST", settings.gigachat_oauth_url, headers=headers, data=payload, verify=False)
    return response.json()['access_token']
