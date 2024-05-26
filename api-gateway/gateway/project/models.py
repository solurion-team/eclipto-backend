from pydantic import BaseModel, Field
from enum import Enum

class Privilege(str, Enum):
    ADMIN = "ADMIN"
    WRITE = "WRITE"
    READ = "READ"

class ProjectInfoDto(BaseModel):
    id: int = Field(
        description="ID of the project", example=7438546582
    )
    name: str = Field(
        description="Project name, which identifies the project to the user", example="eclipto-backend"
    )
    description: str | None = Field(
        default=None, description="A project description that provides more detailed information about the project",
        example="Project about microservice based eclipto backend project, that implement api-gateway pattern and ..."
    )
    tint: str = Field(
        pattern="^#(?:[0-9a-fA-F]{3}){1,2}$",
        description="A color that is convenient for the user to identify the project",
    )
    lead_id: int = Field(
        description="Project lead user id", example=3757385734
    )


class CreateProjectRequest(BaseModel):
    name: str = Field(
        description="Project name, which identifies the project to the user", example="eclipto-backend"
    )
    description: str | None = Field(
        default=None, description="A project description that provides more detailed information about the project",
        example="Project about microservice based eclipto backend project, that implement api-gateway pattern and ..."
    )
    tint: str = Field(
        pattern="^#(?:[0-9a-fA-F]{3}){1,2}$",
        description="A color that is convenient for the user to identify the project",
    )
    workspace_id: int = Field(
        description="Project workspace id",
        example=3757385734
    )
    lead_id: int = Field(
        description="Project lead user id", example=3757385734
    )


class UpdateProjectRequest(BaseModel):
    name: str | None = Field(
        default=None, description="Project name, which identifies the project to the user", example="eclipto-backend")
    description: str | None = Field(
        default=None, description="A project description that provides more detailed information about the project",
        example="Project about microservice based eclipto backend project, that implement api-gateway pattern and ...")
    tint: str | None = Field(
        default=None,
        pattern="^#(?:[0-9a-fA-F]{3}){1,2}$",
        description="A color that is convenient for the user to identify the project",
    )
    lead_id: int | None = Field(
        default=None, description="Project lead user id", example=3757385734
    )

class ProjectAuthorityDto(BaseModel):
    user_id: int = Field(description="Project Authority", example="142313")
    privilege: Privilege = Field(description="Privilege level", example=Privilege)

