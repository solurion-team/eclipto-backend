from datetime import datetime
from typing import List
from enum import Enum

from pydantic import BaseModel, Field


class Priority(str, Enum):
    LOW = "low"
    MEDIUM = "medium"
    HIGH = "high"


class CreateTaskRequest(BaseModel):
    status_id: int = Field(description="Id of the status", example=12345432)
    reporter_user_id: int | None = Field(
        default=None, description="Id of the reporter user", example=123456
    )
    project_id: int = Field(description="Id of the project", example=123456)
    title: str = Field(description="Title of the task", example="Fsdasdasdasdasdasd")


class CreateTaskStatusRequest(BaseModel):
    name: str = Field(description="Name of the task", example="In progress")
    tint: str = Field(description="Tint of the task", example="...")
    project_id: int = Field(description="Id of the project", example=123456)


class UpdateTaskRequest(BaseModel):
    title: str | None = Field(
        default=None, description="Title of the task", example="Learn Java core and Spring Boot"
    )
    description: str | None = Field(
        default=None, description="Description of the task",
        example="example: Go to metanit and learn all the chapters about Java."
    )
    status_id: int | None = Field(
        default=None, description="Id of the status", example=123456
    )
    priority: Priority | None = Field(
        default=None, description="Priority of the task"
    )
    due_date: str = Field(
        description="Due date"
    )
    assigned_user_id: int | None = Field(
        default=None, description="Id of the assigned user", example=123456
    )
    reporter_user_id: int | None = Field(
        default=None, description="Id of the reporter", example=123456
    )


class TaskLiteDto(BaseModel):
    id: int = Field(description="Id of the task", example=12345678)
    title: str = Field(description="Title of the task", example="Learn Java core and Spring Boot")
    priority: Priority = Field(description="Priority of the task")
    assigned_user_id: int = Field(description="Id of the assigned", example=123456)


class TaskStatusDto(BaseModel):
    id: int = Field(description="Id of the task", example=1)
    name: str = Field(description="Name of the task", example="")
    tint: int = Field(description="Tint of the task", example=1)
    tasks: List[TaskLiteDto] | None = Field(default=None, description="List of TaskLiteDto objects")


class TaskInfoDto(BaseModel):
    id: int = Field(description="Id of the task", example=1)
    title: str = Field(description="Title of the task", example="")
    description: str | None = Field(default=None, description="Description of the task", example="Learn Java core and Spring")
    status: TaskStatusDto = Field(description="")
    priority: Priority = Field(description="Priority of the task")
    due_date: str | None = Field(default=None, description="Due date", example="2024-03-28T00:07:19+03:00")
    assigned_user_id: int | None = Field(default=None, description="Id of the assigned user", example=123456)
    reporter_user_id: int = Field(description="Id of the reporter", example=123456)
    created_at: str = Field(description="created_at", example=datetime.now())
    updated_at: str = Field(description="updated_at", example=datetime.now())


class UpdateTaskStatusRequest(BaseModel):
    name: str | None = Field(description="Name of the task status", example="In progress", default=None)
    tint: str | None = Field(description="Color of the task status", example="#FFF", default=None)