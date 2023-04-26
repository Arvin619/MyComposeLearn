package com.example.mycomposelearn.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposelearn.model.domain.usecase.UseCaseLogin
import com.example.mycomposelearn.model.domain.usecase.UseCaseRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(application: Application, private val useCaseLogin: UseCaseLogin, private val useCaseRegister: UseCaseRegister) :
    AndroidViewModel(application) {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String>
        get() = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String>
        get() = _password

    val canLogin = _username.combine(_password) { _, _ ->
        checkUserName() && checkPassword()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)


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

    fun login(): Boolean {
        val result = false
        viewModelScope.launch(Dispatchers.IO) {

        }
//        _password.value = ""
        return result
    }
}