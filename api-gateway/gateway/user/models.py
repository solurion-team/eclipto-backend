from pydantic import BaseModel, Field, EmailStr


class UserInfoDto(BaseModel):
    id: int = Field(
        description="ID of the user", example=7438546582
    )
    first_name: str = Field(
        description="User first name", example="John"
    )
    last_name: str | None = Field(
        default=None, description="User last name", example="Lastname"
    )
    email: EmailStr = Field(
        description="User email", example="myemail@mail.com"
    )


class UpdateUserRequest(BaseModel):
    first_name: str | None = Field(
        default=None, description="User first name", example="John"
    )
    last_name: str | None = Field(
        default=None, description="User last name", example="Lastname"
    )


class RegisterRequest(BaseModel):
    first_name: str = Field(
        description="User first name", example="John"
    )
    last_name: str | None = Field(
        default=None, description="User last name", example="Lastname"
    )
    email: EmailStr = Field(
        description="User email", example="myemail@mail.com"
    )
    password: str = Field(
        description="The password that the user will use for further authentication", example="3757385734"
    )


class LoginRequest(BaseModel):
    email: EmailStr = Field(description="User email", example="myemail@mail.com")
    password: str = Field(
        description="The password that the user will use for further authentication", example="3757385734"
    )


class JwtAuthenticationResponse(BaseModel):
    access_token: str = Field(
        description="Signed jwt token", example="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtk"
    )
