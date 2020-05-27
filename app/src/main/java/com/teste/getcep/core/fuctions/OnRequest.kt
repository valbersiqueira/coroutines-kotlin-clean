package com.teste.getcep.core.fuctions

import com.teste.getcep.data.model.error.FailureError
import retrofit2.Call

fun <T, R> onRequest(request: Call<T>, transformer: (T) -> R, default: T): Result<R, FailureError> {
    return try {
        val response = request.execute()
        when (response.isSuccessful) {
            true -> Result.Success(transformer(response.body() ?: default))
            else -> Result.Failure(FailureError.Failure(response.message()))
        }
    } catch (ex: Throwable) {
        Result.Failure(FailureError.FailureServerThrowable(ex))
    }
}