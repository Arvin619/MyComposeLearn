package com.example.mycomposelearn.model.data.local.repository

import com.example.mycomposelearn.model.Result
import com.example.mycomposelearn.model.data.local.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun login(name: String, password: String): Flow<Result<User?>>

    fun register(name: String, password: String): Flow<Result<User>>
}