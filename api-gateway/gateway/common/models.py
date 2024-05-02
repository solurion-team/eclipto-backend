from pydantic import BaseModel, Field


class ErrorDto(BaseModel):
    status: int = Field(example=404)
    error: str = Field(example="Not Found")
    message: str = Field(example="The requested item was not found")
    path: str = Field(example="/some/path")
    timestamp: str = Field(example="2022-01-01T00:00:00Z")
