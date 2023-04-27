package com.example.mycomposelearn.model.domain.usecaseimpl

import com.example.mycomposelearn.model.Result
import com.example.mycomposelearn.model.data.local.entity.User
import com.example.mycomposelearn.model.data.local.repository.UserRepository
import com.example.mycomposelearn.model.domain.usecase.UseCaseRegister
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCaseRegisterImpl @Inject constructor(private val repository: UserRepository): UseCaseRegister {
    override fun execute(name: String, password: String): Flow<Result<User>> {
        return repository.register(name, password)
    }
}