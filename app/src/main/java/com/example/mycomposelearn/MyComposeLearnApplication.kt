package com.example.mycomposelearn

import android.app.Application
import com.example.mycomposelearn.model.AccountComponent
import com.example.mycomposelearn.model.AppModule
import com.example.mycomposelearn.model.DaggerAccountComponent

class MyComposeLearnApplication : Application() {

    lateinit var accountComponent: AccountComponent
        private set

    override fun onCreate() {
        super.onCreate()
        accountComponent = initDagger()
    }

    private fun initDagger() =
        DaggerAccountComponent.builder()
            .appModule(AppModule(this))
            .build()
}