package com.verygood.myapplication.model

sealed class InternalResult<out R>() {

    data class Success<out T>(val data: T) : InternalResult<T>()
    data class Error(val msg: String?) : InternalResult<Nothing>()
}
