package com.example.pointofsale.repository

sealed class Pretty<out T> {
    abstract val success: Boolean

    class Success<out T>(val value: T) : Pretty<T>() {
        override val success: Boolean
            get() = true
    }

    class Error<out T>(val throwable: Throwable) : Pretty<T>() {
        override val success: Boolean
            get() = false
    }
}