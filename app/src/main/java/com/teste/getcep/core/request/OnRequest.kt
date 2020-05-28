package com.teste.getcep.core.request

import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.data.model.error.FailureError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred

interface OnRequest {

    suspend fun <T> onRequest(
        request: Deferred<T>,
        result: Result<T?, FailureError>.() -> Unit
    )
}

interface CallApi {
    suspend fun <T> safeCallApi(
        dispatcher: CoroutineDispatcher,
        api: suspend () -> T
    ): Result<T, FailureError>
}

