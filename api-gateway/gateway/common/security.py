from typing import Optional
from starlette.requests import Request

from fastapi.security import HTTPBearer, HTTPAuthorizationCredentials


class GatewayHTTPBearer(HTTPBearer):
    mock_credentials = HTTPAuthorizationCredentials(scheme="bearer", credentials="mock")

    async def __call__(self, request: Request) -> Optional[HTTPAuthorizationCredentials]:
        return self.mock_credentials


bearer_auth_scheme = GatewayHTTPBearer()
