from http import HTTPMethod, HTTPStatus
from typing import Annotated
from typing import List

from fastapi import APIRouter, Body, Path, Depends, Query
from starlette.requests import Request
from starlette.responses import Response

from config import settings
from gateway.common.models import ErrorDto
from gateway.common.security import bearer_auth_scheme
from gateway.core.gateway import gate_to
from .models import UpdateProjectRequest, CreateProjectRequest, ProjectInfoDto, ProjectAuthorityDto

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
    operation_id="getProjectInfo",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects/{project_id}"
)
async def get_project(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
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
    status_code=HTTPStatus.OK,
    tags=["project"],
    summary="Update project",
    description="Update project info",
    operation_id="updateProjectInfo",
)
@gate_to(
    method=HTTPMethod.PUT,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects/{project_id}"
)
async def update_project(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        project_id: Annotated[
            int, Path(description="ID of a project", gt=0, example=123)
        ],
        update_project_request_body: Annotated[UpdateProjectRequest, Body()]
) -> ProjectInfoDto:
    pass


# noinspection PyUnusedLocal
@router.post(
    "/v1/projects",
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
    description="Create project with required information",
    operation_id="postProject",
)
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects"
)
async def create_project(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        create_project_request_body: Annotated[CreateProjectRequest, Body()]
) -> ProjectInfoDto:
    pass


# noinspection PyUnusedLocal
@router.delete(
    "/v1/projects/{project_id}",
    # response_model=ProjectInfoDto,
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
        token: Annotated[str, Depends(bearer_auth_scheme)],
        project_id: Annotated[
            int, Path(description="ID of a project", gt=0, example=123)
        ]
) -> None:
    pass


# noinspection PyUnusedLocal
@router.get(
    "/v1/projects",
    response_model=List[ProjectInfoDto],
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
    summary="Get all projects",
    description="Get all user project (or all workspace`s project)",
    operation_id="getProjects",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects"
)
async def get_projects(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        workspaceId: int = Query(
            title="Workspace ID",
            description="The ID of the workspace to filter projects by", example=123
        ),
) -> List[ProjectInfoDto]:
    pass


@router.get(
    "/v1/projects/{project_id}/authorities",
    response_model=List[ProjectAuthorityDto],
    responses={
        403: {
            "description": "Project authorities not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["project"],
    summary="Get full project authorities",
    description="Get full project info authorities",
    operation_id="getProjectAuthorities",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects/{project_id}/authorities"
)
async def get_project_authorities(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        project_id: Annotated[
            int, Path(description="ID of a project", gt=0, example=123)
        ],
) -> List[ProjectAuthorityDto]:
    pass


@router.post(
    "/v1/projects/{project_id}/authorities",
    response_model=ProjectAuthorityDto,
    responses={
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["project"],
    summary="Create project authority",
    description="Create project authorities with required information",
    operation_id="postProjectAuthorities",
)
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects/{project_id}/authorities"
)
async def create_project_authorities(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        project_id: Annotated[
            int, Path(description="ID of a project", gt=0, example=123)
        ],
        create_project_authority_request_body: Annotated[ProjectAuthorityDto, Body()]
) -> ProjectAuthorityDto:
    pass


@router.put(
    "/v1/projects/{project_id}/authorities",
    response_model=ProjectAuthorityDto,
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
    status_code=HTTPStatus.OK,
    tags=["project"],
    summary="Update project authorities",
    description="Update project authorities info",
    operation_id="updateProjectInfoAuthorities",
)
@gate_to(
    method=HTTPMethod.PUT,
    service_url=SERVICE_URL,
    gateway_path="/v1/projects/{project_id}/authorities"
)
async def update_project_authorities(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        project_id: Annotated[
            int, Path(description="ID of a project", gt=0, example=123)
        ],
        update_project_authority_request_body: Annotated[ProjectAuthorityDto, Body()]
) -> ProjectAuthorityDto:
    pass
