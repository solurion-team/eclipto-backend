import asyncio
from http import HTTPMethod, HTTPStatus
from typing import List
from typing import Annotated

from fastapi import APIRouter, Body, Path, Depends, Query
from starlette.requests import Request
from starlette.responses import Response

from config import settings
from gateway.common.models import ErrorDto
from gateway.common.security import bearer_auth_scheme
from gateway.core.gateway import gate_to
from .models import UpdateWorkspaceRequest, CreateWorkspaceRequest, WorkspaceInfoDto, WorkspaceAuthorityDto, \
    UserInfoDto, WorkspaceExtendedDto, map_dto_to_extended
from gateway.user.routes import get_users_by_ids, get_user

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
        token: Annotated[str, Depends(bearer_auth_scheme)],
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
        token: Annotated[str, Depends(bearer_auth_scheme)],
        workspace_id: Annotated[
            int, Path(description="ID of a workspace", gt=0, example=123)
        ],
        update_workspace_request_body: Annotated[UpdateWorkspaceRequest, Body()]
) -> WorkspaceInfoDto:
    pass


# noinspection PyUnusedLocal
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspaces"
)
async def create_workspace(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        create_workspace_request_body: Annotated[CreateWorkspaceRequest, Body()]
) -> WorkspaceInfoDto:
    pass


@router.post(
    "/v1/workspaces",
    response_model=WorkspaceExtendedDto,
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
async def create_workspace_extended(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        create_workspace_request_body: Annotated[CreateWorkspaceRequest, Body()]
) -> WorkspaceExtendedDto:
    workspace: WorkspaceInfoDto = await create_workspace(request, response, token=token,
                                                         create_workspace_request_body=create_workspace_request_body)
    user: UserInfoDto = await get_user(request, response, token=token, user_id=workspace.owner_id)
    return map_dto_to_extended(workspace, user)


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
        token: Annotated[str, Depends(bearer_auth_scheme)],
        workspace_id: Annotated[
            int, Path(description="ID of a workspace", gt=0, example=123)
        ]
        # ) -> WorkspaceInfoDto:
) -> None:
    pass


# noinspection PyUnusedLocal
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspaces"
)
async def get_workspaces(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)]
) -> List[WorkspaceInfoDto]:
    pass


# noinspection PyUnusedLocal
@router.get(
    "/v1/workspaces/",
    response_model=List[WorkspaceExtendedDto],
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
    summary="Get user workspaces",
    description="Get all user workspaces",
    operation_id="getWorkspaces",
)
async def get_extended_workspaces(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)]
) -> List[WorkspaceExtendedDto]:
    workspaces: List[WorkspaceInfoDto] = await get_workspaces(request, response, token=token)
    if not workspaces:
        return list()

    user_ids: List[str] = list(map(lambda workspace: workspace.owner_id, workspaces))
    print(user_ids)
    print(",".join(map(str, user_ids)))
    users: List[UserInfoDto] = await get_users_by_ids(request, response, token=token,
                                                      ids=",".join(map(str, user_ids)))
    users_dict = {user.id: user for user in users}

    async def create_extended_workspace(workspace):
        return map_dto_to_extended(workspace, users_dict[workspace.owner_id])

    extended_workspaces = await asyncio.gather(*[create_extended_workspace(workspace) for workspace in workspaces])
    return list(extended_workspaces)


# noinspection PyUnusedLocal
@router.get(
    "/v1/workspace/{workspace_id}/authorities",
    response_model=List[WorkspaceAuthorityDto],
    responses={
        403: {
            "description": "Workspace authorities not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["workspace"],
    summary="Get information about users authorities",
    description="Get information about users authorities",
    operation_id="getWorkspaceAuthorities",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspace/{workspace_id}/authorities"
)
async def get_workspace_authorities(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        workspace_id: Annotated[
            int, Path(description="ID of a workspace", gt=0, example=123)
        ]
) -> List[WorkspaceAuthorityDto]:
    pass


# noinspection PyUnusedLocal
@router.post(
    "/v1/workspace/{workspace_id}/authorities",
    response_model=WorkspaceAuthorityDto,
    responses={
        403: {
            "description": "Workspace authorities not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["workspace"],
    summary="Create information about users authorities",
    description="Create information about users authorities",
    operation_id="createWorkspaceAuthorities",
)
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspace/{workspace_id}/authorities"
)
async def post_workspace_authorities(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        workspace_id: Annotated[
            int, Path(description="ID of a workspace", gt=0, example=123)
        ],
        create_workspace_authority_request_body: Annotated[WorkspaceAuthorityDto, Body()]
) -> WorkspaceAuthorityDto:
    pass


# noinspection PyUnusedLocal
@router.put(
    "/v1/workspace/{workspace_id}/authorities",
    response_model=WorkspaceAuthorityDto,
    responses={
        403: {
            "description": "Workspace authorities not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["workspace"],
    summary="Update information about users authorities",
    description="Update information about users authorities",
    operation_id="updateWorkspaceAuthorities",
)
@gate_to(
    method=HTTPMethod.PUT,
    service_url=SERVICE_URL,
    gateway_path="/v1/workspace/{workspace_id}/authorities"
)
async def update_workspace_authorities(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        workspace_id: Annotated[
            int, Path(description="ID of a workspace", gt=0, example=123)
        ],
        update_workspace_authority_request_body: Annotated[WorkspaceAuthorityDto, Body()]
) -> WorkspaceAuthorityDto:
    pass
