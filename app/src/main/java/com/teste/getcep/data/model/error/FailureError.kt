package com.teste.getcep.data.model.error

import com.teste.getcep.core.request.ErrorServe


sealed class FailureError {
    data class Failure(val error: String?) : FailureError()
    data class FailureServer(val error: ErrorServe?) : FailureError()
    data class FailureServerThrowable(val error: Throwable?) : FailureError()
    object ShowNetworkError : FailureError()
}