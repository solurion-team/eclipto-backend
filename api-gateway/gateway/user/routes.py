from http import HTTPMethod, HTTPStatus
from typing import Annotated

from fastapi import APIRouter, Body, Path, Depends
from starlette.requests import Request
from starlette.responses import Response

from config import settings
from gateway.common.models import ErrorDto
from gateway.common.security import bearer_auth_scheme
from gateway.core.gateway import gate_to
from .models import LoginRequest, RegisterRequest, UpdateUserRequest, UserInfoDto, JwtAuthenticationResponse

SERVICE_URL = settings.user_service_url

router = APIRouter()


# noinspection PyUnusedLocal
@router.post(
    "/v1/login",
    response_model=JwtAuthenticationResponse,
    responses={
        403: {
            "description": "Wrong password or email",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["auth"],
    summary="Login",
    description="Login using the provided credentials",
    operation_id="login",
)
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/auth/login"
)
async def login(
        request: Request,
        response: Response,
        login_request_body: Annotated[
            LoginRequest,
            Body(
                media_type="application/json",
                description="Success authentication response"
            )
        ]
) -> JwtAuthenticationResponse:
    pass


# noinspection PyUnusedLocal
@router.post(
    "/v1/register",
    response_model=JwtAuthenticationResponse,
    responses={
        400: {
            "description": "User with same email already exists",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.CREATED,
    tags=["auth"],
    summary="Register a user",
    description="Register a new user in the system",
    operation_id="register",
)
@gate_to(
    method=HTTPMethod.POST,
    service_url=SERVICE_URL,
    gateway_path="/v1/auth/register"
)
async def register(
        request: Request,
        response: Response,
        register_request_body: Annotated[
            RegisterRequest,
            Body(
                media_type="application/json",
                description="User registration info"
            )
        ]
) -> JwtAuthenticationResponse:
    pass


# noinspection PyUnusedLocal
@router.get(
    "/v1/users/{user_id}",
    response_model=UserInfoDto,
    responses={
        403: {
            "description": "User not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.OK,
    tags=["user"],
    summary="Get user info",
    description="Get user info",
    operation_id="getUser",
)
@gate_to(
    method=HTTPMethod.GET,
    service_url=SERVICE_URL,
    gateway_path="/v1/users/{user_id}"
)
async def get_user(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        user_id: Annotated[
            int, Path(description="ID of a user", gt=0, example=123)
        ],
) -> UserInfoDto:
    pass


# noinspection PyUnusedLocal
@router.put(
    "/v1/users/{user_id}",
    response_model=UserInfoDto,
    responses={
        403: {
            "description": "User not found",
            "model": ErrorDto,
        },
        "default": {
            "description": "Unexpected error",
            "model": ErrorDto,
        }
    },
    status_code=HTTPStatus.CREATED,
    tags=["user"],
    summary="Update user",
    description="Update user info",
    operation_id="updateUser",
)
@gate_to(
    method=HTTPMethod.PUT,
    service_url=SERVICE_URL,
    gateway_path="/v1/users/{user_id}"
)
async def update_user(
        request: Request,
        response: Response,
        token: Annotated[str, Depends(bearer_auth_scheme)],
        user_id: Annotated[
            int, Path(description="ID of a user", gt=0, example=123)
        ],
        update_user_request_body: Annotated[UpdateUserRequest, Body()]
) -> UserInfoDto:
    pass
