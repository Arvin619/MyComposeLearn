package com.example.mycomposelearn.model.domain.di

import com.example.mycomposelearn.model.domain.usecase.UseCaseLogin
import com.example.mycomposelearn.model.domain.usecase.UseCaseRegister
import com.example.mycomposelearn.model.domain.usecaseimpl.UseCaseLoginImpl
import com.example.mycomposelearn.model.domain.usecaseimpl.UseCaseRegisterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun getUseCaseLogin(useCaseLoginImpl: UseCaseLoginImpl): UseCaseLogin

    @Singleton
    @Binds
    abstract fun getUseCaseRegister(useCaseRegisterImpl: UseCaseRegisterImpl): UseCaseRegister
}