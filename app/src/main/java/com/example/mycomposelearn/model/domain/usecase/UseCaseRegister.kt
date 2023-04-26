package com.example.mycomposelearn.model.domain.usecase

import com.example.mycomposelearn.model.Result
import com.example.mycomposelearn.model.data.local.entity.User
import kotlinx.coroutines.flow.Flow

interface UseCaseRegister {
    fun execute(name: String, password: String): Flow<Result<User>>
}