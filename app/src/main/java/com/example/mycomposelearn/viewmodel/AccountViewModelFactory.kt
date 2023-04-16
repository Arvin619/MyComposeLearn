package com.example.mycomposelearn.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycomposelearn.model.AccountService

@Suppress("UNCHECKED_CAST")
class AccountViewModelFactory(private val accountService: AccountService) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(accountService) as T
        }
        throw IllegalArgumentException("Unknown view model class type")
    }
}