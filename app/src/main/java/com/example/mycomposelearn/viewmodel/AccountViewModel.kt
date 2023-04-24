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
    private val inputUserName = MutableLiveData("")
    val inputUserNameData: LiveData<String>
        get() = inputUserName

    private val inputPassword = MutableLiveData("")
    val inputPasswordData: LiveData<String>
        get() = inputPassword

    private val canLoginButtonEnable = MediatorLiveData(false)
    val canLoginButtonEnableData: LiveData<Boolean>
        get() = canLoginButtonEnable

    private val lastTimeLoginIsFail = MediatorLiveData(false)
    val lastTimeLoginIsFailData: LiveData<Boolean>
        get() = lastTimeLoginIsFail

    init {
        canLoginButtonEnable.addSource(inputUserName) {
            canLoginButtonEnable.value = checkUserName() && checkPassword()
        }

        canLoginButtonEnable.addSource(inputPassword) {
            canLoginButtonEnable.value = checkUserName() && checkPassword()
        }

        lastTimeLoginIsFail.addSource(inputUserName) {
            lastTimeLoginIsFail.value = false
        }

        lastTimeLoginIsFail.addSource(inputPassword) {
            lastTimeLoginIsFail.value = false
        }


    }

    fun updateUserName(newUserName: String) {
        inputUserName.value = newUserName
    }

    fun updatePassword(newPassword: String) {
        inputPassword.value = newPassword
    }

    private fun checkUserName(): Boolean {
        return !inputUserName.value.isNullOrEmpty()
    }

    private fun checkPassword(): Boolean {
        return !inputPassword.value.isNullOrEmpty()
    }

    suspend fun login(): Boolean {
        val result = viewModelScope.async(Dispatchers.IO) {
            if (inputUserName.value.isNullOrEmpty() || inputPassword.value.isNullOrEmpty()) {
                return@async false
            }
            return@async accountService.login(inputUserName.value!!, inputPassword.value!!)
        }.await()

        inputPassword.value = ""
        if (!result) {
            lastTimeLoginIsFail.value = true
        }

        return result
    }
}