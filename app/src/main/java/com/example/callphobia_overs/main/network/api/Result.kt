package com.example.callphobia_overs.main.network.api

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String?) : Result<Nothing>()
    data class Exception(val e: Throwable) : Result<Nothing>()
}