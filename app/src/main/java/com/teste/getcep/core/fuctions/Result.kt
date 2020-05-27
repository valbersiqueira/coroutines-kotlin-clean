package com.teste.getcep.core.fuctions


sealed class Result<out R, out T> {
    data class Success<out R>(val value: R) : Result<R, Nothing>()
    data class Failure<out T>(val value: T) : Result<Nothing, T>()
}

inline fun <R, T, S> Result<R, T>.flow(
    success: (R) -> S,
    failure: (T) -> S
): S = when (this) {
    is Result.Success -> success(value)
    is Result.Failure -> failure(value)
}