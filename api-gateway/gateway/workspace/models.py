from pydantic import BaseModel, Field


class WorkspaceInfoDto(BaseModel):
    id: int = Field(
        description="ID of the workspace", example=7438546582
    )
    name: str = Field(
        description="Workspace name, which identifies the workspace to the user", example="eclipto-backend"
    )
    description: str = Field(
        default=None, description="A workspace description that provides more detailed information about the workspace",
        example="Workspace of solurion company"
    )
    owner_id: int = Field(
        description="Workspace owner user id", example=3757385734
    )


class CreateWorkspaceRequest(BaseModel):
    name: str = Field(
        description="Workspace name, which identifies the workspace to the user", example="eclipto-backend")
    description: str = Field(
        default=None,
        description="A workspace description that provides more detailed information about the workspace",
        example="Workspace of solurion company")


class UpdateWorkspaceRequest(BaseModel):
    name: str = Field(
        default=None, description="Workspace name, which identifies the workspace to the user",
        example="eclipto-backend")
    description: str = Field(
        default=None,
        description="A workspace description that provides more detailed information about the workspace",
        example="Workspace about microservice based eclipto backend workspace, that implement api-gateway pattern and "
                "..")