package com.example.mycomposelearn.model

import android.app.Application
import com.example.mycomposelearn.MyComposeLearnApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: MyComposeLearnApplication) {

    @Provides
    fun getApp(): Application = application

    @Provides
    fun getMCLApp(): MyComposeLearnApplication = application
}