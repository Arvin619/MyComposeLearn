package com.example.mycomposelearn.model.domain.usecaseimpl

import com.example.mycomposelearn.model.Result
import com.example.mycomposelearn.model.data.local.entity.User
import com.example.mycomposelearn.model.data.local.repository.UserRepository
import com.example.mycomposelearn.model.domain.usecase.UseCaseLogin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCaseLoginImpl @Inject constructor(private val repository: UserRepository): UseCaseLogin {
    override fun execute(name: String, password: String, f: Flow<Int>): Flow<Result<User>> {
        return repository.login(name, password)
    }
}