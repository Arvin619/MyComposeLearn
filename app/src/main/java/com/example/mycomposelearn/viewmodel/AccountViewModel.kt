package com.example.mycomposelearn.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mycomposelearn.model.AccountService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class AccountViewModel(application: Application, private val accountService: AccountService) :
    AndroidViewModel(application) {
    private val _username = MutableLiveData("")
    val username: LiveData<String>
        get() = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String>
        get() = _password

    private val _canLogin = MediatorLiveData(false)
    val canLogin: LiveData<Boolean>
        get() = _canLogin

    private val _lastLoginIsFailed = MediatorLiveData(false)
    val lastLoginIsFailed: LiveData<Boolean>
        get() = _lastLoginIsFailed

    init {
        _canLogin.addSource(_username) {
            _canLogin.value = checkUsername() && checkPassword()
        }

        _canLogin.addSource(_password) {
            _canLogin.value = checkUsername() && checkPassword()
        }

        _lastLoginIsFailed.addSource(_username) {
            _lastLoginIsFailed.value = false
        }

        _lastLoginIsFailed.addSource(_password) {
            _lastLoginIsFailed.value = false
        }


    }

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    private fun checkUsername(): Boolean {
        return !_username.value.isNullOrEmpty()
    }

    private fun checkPassword(): Boolean {
        return !_password.value.isNullOrEmpty()
    }

    suspend fun login(): Boolean {
        val result = viewModelScope.async(Dispatchers.IO) {
            if (_username.value.isNullOrEmpty() || _password.value.isNullOrEmpty()) {
                return@async false
            }
            return@async accountService.login(_username.value!!, _password.value!!)
        }.await()

        _password.value = ""
        if (!result) {
            _lastLoginIsFailed.value = true
        }

        return result
    }
}