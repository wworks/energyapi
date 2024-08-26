from fastapi import FastAPI, HTTPException, Depends, Query, Body
from app.database import user_collection
from app.models import UserModel
from app.schema import UserCreate, UserUpdate
from app.auth import create_access_token, get_current_user, get_current_active_user, get_current_active_admin
from bson.objectid import ObjectId
from pymongo.errors import DuplicateKeyError
import os
from typing import Any

app = FastAPI()

# Update the MongoDB connection string to use an environment variable
MONGO_DETAILS = os.getenv("MONGO_URL", "mongodb://localhost:27017/mydatabase")

@app.get("/users/")
async def get_all_users():
    users = []
    async for user in user_collection.find({"role": "user"}, {"name": 1, "_id": 0}):  # Filter by role and select only the name field
        users.append(user["name"])
    return users



@app.get("/admin/")
async def get_all_users(current_user: dict = Depends(get_current_active_admin)):
    users = []
    async for user in user_collection.find():
        user["_id"] = str(user["_id"])  # Convert ObjectId to str
        users.append(UserModel(**user))
    return users

@app.get("/users/{user_id}")
async def get_user_by_id(user_id: str, current_user: dict = Depends(get_current_active_user)):
    user = await user_collection.find_one({"_id": ObjectId(user_id)})
    if user:
        user["_id"] = str(user["_id"])  # Convert ObjectId to str
        return UserModel(**user)
    raise HTTPException(status_code=404, detail="User not found")

# NoSQL Injection vulnerability
@app.get("/search/")
async def search_users(name: str = Query(None), current_user: dict = Depends(get_current_active_admin)):
    # Vulnerable to NoSQL Injection
    query = {"name": {"$regex": name}} if name else {}
    users = []
    async for user in user_collection.find(query):
        user["_id"] = str(user["_id"])  # Convert ObjectId to str
        users.append(UserModel(**user))
    return users


# NoSQL Injection vulnerability

@app.post("/login/")
async def login(name: str = Body(...), password: Any = Body(...)):
    if not name or not password:
        raise HTTPException(status_code=400, detail="Name and password must be provided")
    if isinstance(password, str):
        user = await user_collection.find_one({"name": {"$regex": name}, "password": password})
    else:
        user = await user_collection.find_one({"name": {"$regex": name}, "password": password})
    if user:
        token = create_access_token({
            "sub": user["name"], 
            "role": user["role"]
        })
        return {"access_token": token, "token_type": "bearer"}
    raise HTTPException(status_code=401, detail="Invalid credentials")


# NoSQL Injection vulnerability
@app.get("/login/")
async def login(name: str = Query(None), password: Any = Query(None)):
    if not name or not password:
        raise HTTPException(status_code=400, detail="Name and password must be provided")
    user = await user_collection.find_one({"name": {"$regex": name}, "password": password})
    if user:
        token = create_access_token({
            "sub": user["name"], 
            "role": user["role"]
        })
        return {"access_token": token, "token_type": "bearer"}
    raise HTTPException(status_code=401, detail="Invalid credentials")