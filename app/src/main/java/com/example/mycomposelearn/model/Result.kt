package com.example.mycomposelearn.model

sealed interface Result<in T> {
    object Init: Result<Any?>
    object Loading: Result<Any?>
    data class Success<T>(val data: T): Result<T>
    data class Error(val e: Throwable): Result<Any?>
}