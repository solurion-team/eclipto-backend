from http import HTTPStatus, HTTPMethod
from typing import Annotated

from fastapi import APIRouter, Body, Path, Query, Depends
from starlette.requests import Request
from starlette.responses import Response

from config import settings
from gateway.common.models import ErrorDto
from gateway.core.gateway import gate_to
from .models import *
from gateway.common.security import bearer_auth_scheme

SERVICE_URL = settings.task_service_url

router = APIRouter()


# noinspection PyUnusedLocal
@router.get(
    "/v1/tasks/lite",
    response_model=List[TaskLiteDto],
    responses={
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="List of task lite",
    description="Get all project tasks with lite information",
    operation_id="getLiteTasks",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/tasks/lite"
)
async def get_lite_tasks(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        projectId: Annotated[int, Query(description="Project ID")] = None
) -> List[TaskLiteDto]:
    pass


# noinspection PyUnusedLocal
@router.get(
    "/v1/tasks/{task_id}",
    response_model=TaskInfoDto,
    responses={
        403: {
            "description": "Task not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="Task",
    description="Get full task information",
    operation_id="getTask",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/tasks/{task_id}"
)
async def get_task(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        task_id: Annotated[
            int, Path(description="ID of a task", example=123, gt=0)
        ]
) -> TaskInfoDto:
    pass


# noinspection PyUnusedLocal
@router.put(
    "/v1/tasks/{task_id}",
    response_model=TaskInfoDto,
    responses={
        403: {
            "description": "There is no task with same ID or status with same ID",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="Update task",
    description="Update task information",
    operation_id="updateTask",
)
@gate_to(
    method=HTTPMethod.PUT,
    service_url=SERVICE_URL,
    gateway_path="/v1/tasks/{task_id}"
)
async def update_task(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        task_id: Annotated[
            int, Path(description="ID of a task", example=123, gt=0)
        ],
        update_task_request_body: Annotated[UpdateTaskRequest, Body()]
) -> TaskInfoDto:
    pass


# noinspection PyUnusedLocal
@router.delete(
    "/v1/tasks/{task_id}",
    response_model=None,
    responses={
        403: {
            "description": "Task not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="Task delete",
    description="Permanently delete task",
    operation_id="deleteTask",
)
@gate_to(
    method=HTTPMethod.DELETE,
    service_url=SERVICE_URL,
    gateway_path="/v1/tasks/{task_id}"
)
async def delete_task(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        task_id: Annotated[
            int, Path(description="ID of a task", example=123, gt=0)
        ]
) -> None:
    pass


# noinspection PyUnusedLocal
@router.get(
    "/v1/tasks",
    response_model=List[TaskInfoDto],
    responses={
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="Tasks",
    description="Get all project tasks with full information",
    operation_id="getAllTasks",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/tasks"
)
async def get_all_tasks(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        projectId: Annotated[int, Query()] = None,
        isCompleted: Annotated[bool, Query()] = None,
) -> List[TaskInfoDto]:
    pass


# noinspection PyUnusedLocal
@router.post(
    "/v1/tasks",
    response_model=TaskLiteDto,
    responses={
        403: {
            "description": "There is no status with same ID",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="Task lite create",
    description="Request to create a task",
    operation_id="postTask",
)
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/tasks"
)
async def create_task(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        create_task_request_body: Annotated[CreateTaskRequest, Body()]
) -> TaskLiteDto:
    pass


# noinspection PyUnusedLocal
@router.post(
    "/v1/tasks/statuses",
    response_model=TaskStatusDto,
    responses={
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="Created task status",
    description="Create tasks status",
    operation_id="postTaskStatus",
)
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/tasks/statuses"
)
async def post_task_status(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        create_task_status_request_body: Annotated[CreateTaskStatusRequest, Body()]
) -> TaskStatusDto:
    pass


# noinspection PyUnusedLocal
@router.put(
    "/v1/tasks/statuses/{status_id}",
    response_model=TaskStatusDto,
    responses={
        403: {
            "description": "There is no status with same ID",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="Updated task",
    description="Update task status information",
    operation_id="updateTaskStatus",
)
@gate_to(
    method=HTTPMethod.PUT,
    service_url=SERVICE_URL,
    gateway_path="/v1/tasks/statuses/{status_id}"
)
async def update_task_status(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        status_id: Annotated[
            int, Path(description="ID of a status", example=123, gt=0)
        ],
        update_task_status_request_body: Annotated[UpdateTaskStatusRequest, Body()]
) -> TaskStatusDto:
    pass


# noinspection PyUnusedLocal
@router.get(
    "/v1/projects/{project_id}/statuses",
    response_model=List[TaskStatusDto],
    responses={
        403: {
            "description": "There is no status with same ID",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["task"],
    summary="List of project statuses",
    description="Get task statuses",
    operation_id="getProjectTaskStatuses",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects/{project_id}/statuses"
)
async def get_project_task_statuses(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        project_id: Annotated[
            int, Path(description="ID of a project", example=123, gt=0)
        ],
        includeTasks: Annotated[bool, Query()] = None,
        isCompleted: Annotated[bool, Query()] = None,
) -> List[TaskStatusDto]:
    pass
