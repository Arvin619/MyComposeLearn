package com.example.mycomposelearn.model.domain.usecase

import com.example.mycomposelearn.model.Result
import com.example.mycomposelearn.model.data.local.entity.User
import kotlinx.coroutines.flow.Flow

interface UseCaseLogin {
    fun execute(name: String, password: String, f: Flow<Int>): Flow<Result<User>>
}