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
    private val inputUserName = MutableStateFlow("")
    val inputUserNameData: StateFlow<String>
        get() = inputUserName

    private val inputPassword = MutableStateFlow("")
    val inputPasswordData: StateFlow<String>
        get() = inputPassword

    private val canLoginButtonEnable = MutableStateFlow(false)
    val canLoginButtonEnableData: StateFlow<Boolean>
        get() = canLoginButtonEnable

    private val lastTimeLoginIsFail = MutableStateFlow(false)
    val lastTimeLoginIsFailData: StateFlow<Boolean>
        get() = lastTimeLoginIsFail

    init {
        viewModelScope.launch {
            combine(inputUserNameData, inputPasswordData) { userName, password ->
                userName+password
            }.collect {
                canLoginButtonEnable.value = checkUserName() && checkPassword()
                lastTimeLoginIsFail.value = false
            }
        }
    }

    fun setInputUserName(newUserName: String) {
        inputUserName.value = newUserName
    }

    fun setInputPassword(newPassword: String) {
        inputPassword.value = newPassword
    }

    private fun checkUserName(): Boolean {
        return inputUserName.value.isNotEmpty()
    }

    private fun checkPassword(): Boolean {
        return inputPassword.value.isNotEmpty()
    }

    suspend fun login(): Boolean {
        val result = viewModelScope.async(Dispatchers.IO) {
            if (inputUserName.value.isEmpty() || inputPassword.value.isEmpty()) {
                return@async false
            }
            return@async accountService.login(inputUserName.value, inputPassword.value)
        }.await()

        inputPassword.value = ""
        if (!result) {
            lastTimeLoginIsFail.value = true
        }

        return result
    }
}