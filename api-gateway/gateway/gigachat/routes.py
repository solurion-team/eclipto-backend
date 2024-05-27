from fastapi import APIRouter
from config import settings

from langchain.schema import HumanMessage
from langchain.chat_models.gigachat import GigaChat
from langchain_core.prompts import load_prompt

router = APIRouter()
prompt_for_description_task = load_prompt(
    "C:\\Users\slava\OneDrive\Desktop\project\eclipto-backend\\api-gateway\gateway\gigachat\config\\task_description_prompt.yaml")
c = GigaChat(credentials=settings.GIGACHAT_API_KEY, verify_ssl_certs=False)

@router.get("/gigachat/test")
async def gigachat_task_description(query: str):
    try:
        text = prompt_for_description_task.format(title=query)
        return c([HumanMessage(content=text)])
    except Exception as e:
        raise e