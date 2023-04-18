package com.example.mycomposelearn.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycomposelearn.model.AccountService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountViewModelFactory @Inject constructor(private val application: Application, private val accountService: AccountService) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(application, accountService) as T
        }
        throw IllegalArgumentException("Unknown view model class type")
    }
}