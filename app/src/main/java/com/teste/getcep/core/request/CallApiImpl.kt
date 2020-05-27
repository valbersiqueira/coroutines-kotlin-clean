package com.teste.getcep.core.request

import android.util.Log
import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.data.model.error.FailureError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class CallApiImpl: CallApi {

    override suspend fun <T> safeCallApi(
        dispatcher: CoroutineDispatcher,
        api: suspend () -> Deferred<T>
    ): Result<T, FailureError> {
        return withContext(dispatcher) {
            try {
                Result.Success(api.invoke().await())
            } catch (ex: Throwable) {
                when (ex) {
                    is IOException -> Result.Failure(FailureError.ShowNetworkError)
                    is HttpException -> {
                        Result.Failure(FailureError.FailureServer(converterErrorBody(ex)))
                    }
                    else -> Result.Failure(FailureError.Failure(""))
                }
            }
        }
    }

    private fun converterErrorBody(throwable: HttpException): ErrorServe? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                Log.d("ERROR_CONVERTER", it.toString())
                null
            }
        } catch (ex: Exception) {
            null
        }
    }
}