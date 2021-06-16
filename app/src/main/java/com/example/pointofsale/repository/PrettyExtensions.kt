package com.example.pointofsale.repository

inline fun <T> Pretty<T>.handle(
    crossinline success: (T) -> Unit = {},
    crossinline error: (Throwable) -> Unit = {}
) {
    when (this) {
        is Pretty.Error -> error(this.throwable)
        is Pretty.Success -> success(this.value)
    }
}

inline fun CompletedPretty.handle(
    crossinline success: () -> Unit = {},
    crossinline error: (Throwable) -> Unit
) {
    when (this) {
        is CompletedPretty.Error -> error.invoke(this.throwable)
        CompletedPretty.Success -> success()
    }
}