from dynaconf import Dynaconf

settings = Dynaconf(
    load_dotenv=True,
    environments=True,
    envvar_prefix="GATEWAY",
    settings_files=['settings.toml'],
)
