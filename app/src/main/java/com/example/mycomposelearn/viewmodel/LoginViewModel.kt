package com.example.mycomposelearn.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposelearn.model.Result
import com.example.mycomposelearn.model.data.local.entity.User
import com.example.mycomposelearn.model.domain.usecase.UseCaseLogin
import com.example.mycomposelearn.model.domain.usecase.UseCaseRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(application: Application, private val useCaseLogin: UseCaseLogin, private val useCaseRegister: UseCaseRegister) :
    AndroidViewModel(application) {

    private val _status: MutableStateFlow<Result<User>> = MutableStateFlow(Result.Init)
    val status: StateFlow<Result<User>> = _status

    fun login(name: String, password: String): Boolean {
        val result = false
        viewModelScope.launch(Dispatchers.IO) {
            useCaseLogin.execute(name, password).collect {
                Log.i("Arvin-log", "$it")
                _status.value = it
            }
        }
        return result
    }
}