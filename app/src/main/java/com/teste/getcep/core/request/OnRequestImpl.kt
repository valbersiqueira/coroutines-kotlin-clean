package com.teste.getcep.core.request

import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.data.model.error.FailureError
import kotlinx.coroutines.Deferred

class OnRequestImpl : OnRequest {

    override suspend fun <T> onRequest(
        request: Deferred<T>,
        result: Result<T?, FailureError>.() -> Unit
    ) {
        val controlledRunner = ControlledRunner<Deferred<T>>()
        try {
            val response = controlledRunner.cancelPreviousThenRun { request }.await()
            result(Result.Success(response))
        } catch (ex: Throwable) {
            ex.printStackTrace()
            result(Result.Failure(FailureError.FailureServerThrowable(ex)))
        }
    }
}

