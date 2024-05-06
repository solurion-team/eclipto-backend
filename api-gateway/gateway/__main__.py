import uvicorn

from fastapi import FastAPI, Body
from fastapi.security import HTTPBearer
from fastapi.middleware.cors import CORSMiddleware

from user.routes import router as user_router
from workspace.routes import router as workspace_router
from project.routes import router as project_router
from config import settings

app = FastAPI()
app.include_router(user_router)
app.include_router(project_router)
app.include_router(workspace_router)
app.add_middleware(
    CORSMiddleware,
    allow_origins=[settings.frontend_url],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=settings.port)
