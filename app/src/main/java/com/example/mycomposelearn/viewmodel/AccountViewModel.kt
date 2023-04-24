package com.example.mycomposelearn.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposelearn.model.AccountService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class AccountViewModel(application: Application, private val accountService: AccountService) :
    AndroidViewModel(application) {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String>
        get() = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String>
        get() = _password

    private val _canLogin = MutableStateFlow(false)
    val canLogin: StateFlow<Boolean>
        get() = _canLogin

    init {
        viewModelScope.launch {
            combine(username, password) { userName, password ->
                userName+password
            }.collect {
                _canLogin.value = checkUserName() && checkPassword()
            }
        }
    }

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    private fun checkUserName(): Boolean {
        return _username.value.isNotEmpty()
    }

    private fun checkPassword(): Boolean {
        return _password.value.isNotEmpty()
    }

    suspend fun login(): Boolean {
        val result = viewModelScope.async(Dispatchers.IO) {
            if (_username.value.isEmpty() || _password.value.isEmpty()) {
                return@async false
            }
            return@async accountService.login(_username.value, _password.value)
        }.await()

        _password.value = ""
        return result
    }
}