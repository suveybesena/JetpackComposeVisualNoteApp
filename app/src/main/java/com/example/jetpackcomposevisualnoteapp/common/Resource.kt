package com.example.jetpackcomposevisualnoteapp.common

sealed class Resource<T> {
    data class Error<T>(var message: String? = null) : Resource<T>()
    data class Success<T>(var data: T? = null) : Resource<T>()
    data class Loading<T>(var booelan: Boolean? = null) : Resource<T>()
}
