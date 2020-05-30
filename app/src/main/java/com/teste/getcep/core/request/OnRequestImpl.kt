package com.teste.getcep.core.request

import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.data.model.error.FailureError

class OnRequestImpl : OnRequest {

    override suspend fun <T> onRequest(
        request: T,
        result: Result<T?, FailureError>.() -> Unit
    ) {
        val controlledRunner = ControlledRunner<T>()
        try {
            val response = controlledRunner.cancelPreviousThenRun { request }
            result(Result.Success(response))
        } catch (ex: Throwable) {
            ex.printStackTrace()
            result(Result.Failure(FailureError.FailureServerThrowable(ex)))
        }
    }
}

