from http import HTTPMethod, HTTPStatus
from typing import Annotated

from fastapi import APIRouter, Body, Path, Depends
from starlette.requests import Request
from starlette.responses import Response

from config import settings
from gateway.common.models import ErrorDto
from gateway.common.security import bearer_auth_scheme
from gateway.core.gateway import gate_to
from .models import UpdateWorkspaceRequest, CreateWorkspaceRequest, WorkspaceInfoDto

router = APIRouter()

SERVICE_URL = settings.workspace_service_url


# noinspection PyUnusedLocal
@router.get(
    "/v1/workspaces/{workspace_id}",
    response_model=WorkspaceInfoDto,
    responses={
        403: {
            "description": "Workspace not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["workspace"],
    summary="Get full workspace",
    description="Get full workspace info",
    operation_id="getWorkspace",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspaces/{workspace_id}"
)
async def get_workspace(
        request: Request,
        response: Response,
        # token: Annotated[str, Depends(bearer_auth_scheme)],
        workspace_id: Annotated[
            int, Path(description="ID of a workspace", gt=0, example=123)
        ],
) -> WorkspaceInfoDto:
    pass


# noinspection PyUnusedLocal
@router.put(
    "/v1/workspaces/{workspace_id}",
    response_model=WorkspaceInfoDto,
    responses={
        403: {
            "description": "Workspace not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["workspace"],
    summary="Update workspace",
    description="Update workspace info",
    operation_id="updateWorkspace",
)
@gate_to(
    method=HTTPMethod.PUT,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspaces/{workspace_id}"
)
async def update_workspace(
        request: Request,
        response: Response,
        # token: Annotated[str, Depends(bearer_auth_scheme)],
        workspace_id: Annotated[
            int, Path(description="ID of a workspace", gt=0, example=123)
        ],
        update_workspace_request_body: Annotated[UpdateWorkspaceRequest, Body()]
) -> WorkspaceInfoDto:
    pass


# noinspection PyUnusedLocal
@router.post(
    "/v1/workspaces",
    response_model=WorkspaceInfoDto,
    responses={
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["workspace"],
    summary="Create workspace",
    description="Create workspace",
    operation_id="createWorkspace",
)
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspaces"
)
async def create_workspace(
        request: Request,
        response: Response,
        # token: Annotated[str, Depends(bearer_auth_scheme)],
        create_workspace_request_body: Annotated[CreateWorkspaceRequest, Body()]
) -> WorkspaceInfoDto:
    pass


# noinspection PyUnusedLocal
@router.delete(
    "/v1/workspaces/{workspace_id}",
    # response_model=WorkspaceInfoDto,
    responses={
        403: {
            "description": "Workspace not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.NO_CONTENT,
    tags=["workspace"],
    summary="Delete workspace",
    description="Delete workspace",
    operation_id="deleteWorkspace",
)
@gate_to(
    method=HTTPMethod.DELETE,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspaces/{workspace_id}"
)
async def delete_workspace(
        request: Request,
        response: Response,
        # token: Annotated[str, Depends(bearer_auth_scheme)],
        workspace_id: Annotated[
            int, Path(description="ID of a workspace", gt=0, example=123)
        ]
# ) -> WorkspaceInfoDto:
) -> None:
    pass
