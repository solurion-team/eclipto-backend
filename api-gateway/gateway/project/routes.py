from http import HTTPMethod, HTTPStatus
from typing import Annotated

from fastapi import APIRouter, Body, Path, Depends
from starlette.requests import Request
from starlette.responses import Response

from config import settings
from gateway.common.models import ErrorDto
from gateway.common.security import bearer_auth_scheme
from gateway.core.gateway import gate_to
from .models import UpdateProjectRequest, CreateProjectRequest, ProjectInfoDto

router = APIRouter()

SERVICE_URL = settings.project_service_url


# noinspection PyUnusedLocal
@router.get(
    "/v1/projects/{project_id}",
    response_model=ProjectInfoDto,
    responses={
        403: {
            "description": "Project not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["project"],
    summary="Get full project",
    description="Get full project info",
    operation_id="getProject",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects/{project_id}"
)
async def get_project(
        request: Request,
        response: Response,
        # token: Annotated[str, Depends(bearer_auth_scheme)],
        project_id: Annotated[
            int, Path(description="ID of a project", gt=0, example=123)
        ],
) -> ProjectInfoDto:
    pass


# noinspection PyUnusedLocal
@router.put(
    "/v1/projects/{project_id}",
    response_model=ProjectInfoDto,
    responses={
        403: {
            "description": "There is no project with same ID",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.NO_CONTENT,
    tags=["project"],
    summary="Update project",
    description="Update project info",
    operation_id="updateProject",
)
@gate_to(
    method=HTTPMethod.PUT,
    service_url=SERVICE_URL,
    gateway_path="/v1/users/{project_id}"
)
async def update_project(
        request: Request,
        response: Response,
        # token: Annotated[str, Depends(bearer_auth_scheme)],
        project_id: Annotated[
            int, Path(description="ID of a project", gt=0, example=123)
        ],
        update_project_request_body: Annotated[UpdateProjectRequest, Body()]
) -> ProjectInfoDto:
    pass


# noinspection PyUnusedLocal
@router.post(
    "/v1/workspaces/{workspace_id}/projects",
    response_model=ProjectInfoDto,
    responses={
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["project"],
    summary="Create project",
    description="Create project",
    operation_id="createProject",
)
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspaces/{workspace_id}/projects"
)
async def create_project(
        request: Request,
        response: Response,
        # token: Annotated[str, Depends(bearer_auth_scheme)],
        workspace_id: Annotated[
            int, Path(description="ID of a workspace", gt=0, example=123)
        ],
        create_project_request_body: Annotated[CreateProjectRequest, Body()]
) -> ProjectInfoDto:
    pass


# noinspection PyUnusedLocal
@router.delete(
    "/v1/projects/{project_id}",
    response_model=ProjectInfoDto,
    responses={
        403: {
            "description": "Project not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.NO_CONTENT,
    tags=["project"],
    summary="Delete project",
    description="Delete project",
    operation_id="deleteProject",
)
@gate_to(
    method=HTTPMethod.DELETE,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects/{project_id}"
)
async def delete_project(
        request: Request,
        response: Response,
        # token: Annotated[str, Depends(bearer_auth_scheme)],
        project_id: Annotated[
            int, Path(description="ID of a project", gt=0, example=123)
        ]
) -> ProjectInfoDto:
    pass
