package com.example.mycomposelearn.model.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.mycomposelearn.model.data.local.dao.UserDao
import com.example.mycomposelearn.model.data.local.database.AccountDataBase
import com.example.mycomposelearn.model.data.local.repository.UserRepository
import com.example.mycomposelearn.model.data.local.repositoryimppl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Singleton
    @Provides
    fun getDataBase(@ApplicationContext context: Context): AccountDataBase {
        return Room.databaseBuilder(context, AccountDataBase::class.java, "account_database").build()
    }

    @Singleton
    @Provides
    fun getUserDao(accountDataBase: AccountDataBase): UserDao {
        return accountDataBase.userDao
    }

    @Singleton
    @Provides
    fun getUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }
}