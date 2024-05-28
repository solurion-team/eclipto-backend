from pydantic import BaseModel, Field


class TaskDescriptionResult(BaseModel):
    description: str = Field(description="Generated description")


class TaskDescriptionRequest(BaseModel):
    title: str = Field(description="Task title")
