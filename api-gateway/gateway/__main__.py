import uvicorn

from fastapi import FastAPI, Body, HTTPException
from fastapi.security import HTTPBearer
from fastapi.middleware.cors import CORSMiddleware
from starlette.requests import Request
from starlette.responses import JSONResponse

from user.routes import router as user_router
from workspace.routes import router as workspace_router
from project.routes import router as project_router
from task.routes import router as task_router
from gigachat.routes import router as gigachat_router
from config import settings

app = FastAPI()
app.include_router(user_router)
app.include_router(project_router)
app.include_router(workspace_router)
app.include_router(task_router)
app.include_router(gigachat_router)

app.add_middleware(
    CORSMiddleware,
    allow_origins=[settings.frontend_url],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


# noinspection PyUnusedLocal
@app.exception_handler(HTTPException)
async def http_exception_handler(request: Request, exc: HTTPException):
    return JSONResponse(
        status_code=exc.status_code,
        content=exc.detail,
    )


if __name__ == "__main__":
    uvicorn.run(
        app=f"{__name__}:app",
        workers=settings.workers,
        host=settings.host,
        port=settings.port,
        timeout_keep_alive=settings.request_timeout,
        reload=False
    )
