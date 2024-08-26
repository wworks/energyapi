from pydantic import BaseModel, Field
from typing import Optional
from datetime import datetime

class UserModel(BaseModel):
    id: Optional[str] = Field(None, alias="_id")
    name: str
    email: str
    password: str
    role: str

class EnergyModel(BaseModel):
    id: Optional[str]
    timestamp: datetime
    usage: float
    production: float

    class Config:
        schema_extra = {
            "example": {
                "id": "612a1a7e57df584ee7d9c9a1",
                "timestamp": "2023-08-01T12:34:56",
                "usage": 1.5,
                "production": 3.0,
            }
        }