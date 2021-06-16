package com.example.pointofsale.repository

fun <T> Result<T>.toPretty(): Pretty<T> {
    return if (isSuccess) {
        Pretty.Success(this.getOrNull()!!)
    } else {
        Pretty.Error(this.exceptionOrNull()!!)
    }
}

fun Result<*>.toCompletedPretty(): CompletedPretty {
    return if (isSuccess) {
        CompletedPretty.Success
    } else {
        CompletedPretty.Error(this.exceptionOrNull()!!)
    }
}