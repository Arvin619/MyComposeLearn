package com.example.mycomposelearn.model.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycomposelearn.model.data.local.dao.UserDao
import com.example.mycomposelearn.model.data.local.entity.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AccountDataBase: RoomDatabase() {
    abstract val userDao: UserDao
}