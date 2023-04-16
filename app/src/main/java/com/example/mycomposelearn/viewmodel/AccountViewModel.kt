package com.example.mycomposelearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycomposelearn.model.AccountService

class AccountViewModel(private val accountService: AccountService): ViewModel() {
    private val inputUserName = MutableLiveData("")
    val inputUserNameData: LiveData<String>
        get() = inputUserName

    private val inputPassword = MutableLiveData("")
    val inputPasswordData: LiveData<String>
        get() = inputPassword

    fun setInputUserName(newUserName: String) {
        inputUserName.value = newUserName
    }

    fun setInputPassword(newPassword: String) {
        inputPassword.value = newPassword
    }
}