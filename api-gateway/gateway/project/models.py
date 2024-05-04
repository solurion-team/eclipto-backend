from pydantic import BaseModel, Field


class ProjectInfoDto(BaseModel):
    id: int = Field(
        description="ID of the project", example=7438546582
    )
    name: str = Field(
        description="Project name, which identifies the project to the user", example="eclipto-backend"
    )
    description: str = Field(
        default=None, description="A project description that provides more detailed information about the project",
        example="Project about microservice based eclipto backend project, that implement api-gateway pattern and ..."
    )
    lead_id: int = Field(
        description="Project lead user id", example=3757385734
    )


class CreateProjectRequest(BaseModel):
    name: str = Field(
        description="Project name, which identifies the project to the user", example="eclipto-backend")
    description: str = Field(
        default=None,
        description="A project description that provides more detailed information about the project",
        example="Project about microservice based eclipto"
                " backend project, that implements api-gateway pattern and ..."),
    workspace_id: int = Field(
        default=None,
        description="Project workspace id",
        example=3757385734
    )
    lead_id: int = Field(
        description="Project lead user id", example=3757385734
    )


class UpdateProjectRequest(BaseModel):
    name: str = Field(
        default=None, description="Project name, which identifies the project to the user", example="eclipto-backend")
    description: str = Field(
        default=None, description="A project description that provides more detailed information about the project",
        example="Project about microservice based eclipto backend project, that implement api-gateway pattern and ...")
    lead_id: int = Field(
        default=None, description="Project lead user id", example=3757385734
    )
