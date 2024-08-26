from pydantic import BaseModel
from typing import Optional
from datetime import datetime

class UserCreate(BaseModel):
    name: str
    email: str
    password: str
    role: str

    class Config:
        schema_extra = {
            "example": {
                "name": "Alice",
                "email": "alice@example.com",
                "password": "password123",
                "role": "user",
            }
        }

class UserUpdate(BaseModel):
    name: Optional[str]
    email: Optional[str]
    password: Optional[str]
    role: Optional[str]

    class Config:
        schema_extra = {
            "example": {
                "name": "Alice",
                "email": "alice@example.com",
                "password": "newpassword123",
                "role": "admin",
            }
        }

class EnergyCreate(BaseModel):
    usage: float
    production: float

    class Config:
        schema_extra = {
            "example": {
                "usage": 2.5,
                "production": 4.0,
            }
        }