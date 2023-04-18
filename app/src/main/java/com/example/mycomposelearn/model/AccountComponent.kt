package com.example.mycomposelearn.model

import com.example.mycomposelearn.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AccountComponent {
    fun inject(mainActivity: MainActivity)
}