package com.example.mycomposelearn.model.data.local.repositoryimppl

import android.util.Log
import com.example.mycomposelearn.model.Result
import com.example.mycomposelearn.model.data.local.dao.UserDao
import com.example.mycomposelearn.model.data.local.entity.User
import com.example.mycomposelearn.model.data.local.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val dao: UserDao): UserRepository {
    override fun login(name: String, password: String): Flow<Result<User?>> {
        return flow {
            emit(Result.Loading)
            dao.getUser(name, password).collect {
                if (it != null) {
                    emit(Result.Success(it))
                } else {
                    emit(Result.Error(Exception("login failed")))
                }
            }
        }
    }

    override fun register(name: String, password: String): Flow<Result<User>> {
        return flow {
            emit(Result.Loading)
            Log.i("Arvin-log", "Hello World")
            val user = User(0, name, password)
            val rowId = dao.insert(user)
            if (rowId >= 0 ) {
                emit(Result.Success(user.copy(id = rowId.toInt())))
            } else {
                emit(Result.Error(Exception("register failed")))
                Log.i("Arvin-log", user.copy(id = rowId.toInt()).toString())
            }
            Log.i("Arvin-log", "Hello World1")
        }
    }

}