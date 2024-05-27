from enum import Enum

from pydantic import BaseModel, Field, EmailStr

from gateway.user.models import UserInfoDto


class WorkspaceInfoDto(BaseModel):
    id: int = Field(
        description="ID of the workspace", example=7438546582
    )
    name: str = Field(
        description="Workspace name, which identifies the workspace to the user", example="eclipto-backend"
    )
    description: str | None = Field(
        default=None, description="A workspace description that provides more detailed information about the workspace",
        example="Workspace of solurion company"
    )
    owner_id: int = Field(
        description="Workspace owner user id", example=3757385734
    )


class WorkspaceExtendedDto(BaseModel):
    id: int = Field(
        description="ID of the workspace", example=7438546582
    )
    name: str = Field(
        description="Workspace name, which identifies the workspace to the user", example="eclipto-backend"
    )
    description: str | None = Field(
        default=None, description="A workspace description that provides more detailed information about the workspace",
        example="Workspace of solurion company"
    )
    owner: UserInfoDto = Field(
        description="Workspace owner user id"
    )


class CreateWorkspaceRequest(BaseModel):
    name: str = Field(
        description="Workspace name, which identifies the workspace to the user", example="eclipto-backend")
    description: str | None = Field(
        default=None,
        description="A workspace description that provides more detailed information about the workspace",
        example="Workspace of solurion company"
    )


class UpdateWorkspaceRequest(BaseModel):
    name: str | None = Field(
        default=None, description="Workspace name, which identifies the workspace to the user",
        example="eclipto-backend")
    description: str | None = Field(
        default=None,
        description="A workspace description that provides more detailed information about the workspace",
        example="Workspace about microservice based eclipto backend workspace, that implement api-gateway pattern and "
                ".."
    )


class WorkspacePrivilege(str, Enum):
    READ = "READ"
    WRITE = "WRITE"
    ADMIN = "ADMIN"


class WorkspaceAuthorityDto(BaseModel):
    user_id: int = Field(
        description="Id of user",
        example=3757385734
    )
    privilege: WorkspacePrivilege = Field(
        description="Privilege"
    )


def map_dto_to_extended(workspace_dto: WorkspaceInfoDto, owner: UserInfoDto) -> WorkspaceExtendedDto:
    return WorkspaceExtendedDto(
        id=workspace_dto.id,
        name=workspace_dto.name,
        description=workspace_dto.description,
        owner=owner
    )
