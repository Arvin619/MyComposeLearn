package com.example.mycomposelearn.model

import kotlinx.coroutines.delay

class AccountService {
    companion object {
        const val FAKE_USER_NAME = "abc"
        const val FAKE_PASSWORD = "123"
    }

    suspend fun login(userName: String, password: String): Boolean {
        val second = (1..4).shuffled()[0]
        delay(second * 1000L)
        return userName == FAKE_USER_NAME && password == FAKE_PASSWORD
    }
}