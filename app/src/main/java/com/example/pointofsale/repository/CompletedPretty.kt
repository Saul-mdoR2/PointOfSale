package com.example.pointofsale.repository

sealed class CompletedPretty {
    abstract val success: Boolean

    object Success : CompletedPretty() {
        override val success: Boolean
            get() = true
    }

    class Error(val throwable: Throwable) : CompletedPretty() {
        override val success: Boolean
            get() = false
    }
}