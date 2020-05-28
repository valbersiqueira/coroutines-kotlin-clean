package com.teste.getcep.data.repositories


import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.core.fuctions.flow
import com.teste.getcep.core.request.CallApi
import com.teste.getcep.core.request.OnRequest
import com.teste.getcep.data.model.cep.CepResponse
import com.teste.getcep.data.model.error.FailureError
import com.teste.getcep.data.remote.CepServiceApi
import com.teste.getcep.domain.repositories.CepRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CepRepositoryImpl(
    private val request: OnRequest,
    private val cepService: CepServiceApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val callApi: CallApi
) : CepRepository {

    override suspend fun getCep(
        cep: String,
        result: Result<CepResponse?, FailureError>.() -> Unit
    ) {
        request.onRequest(cepService.getCepAsync(cep)) {
            flow({
                result(Result.Success(it))
            }, {
                result(Result.Failure(it))
            })
        }
    }

    override suspend fun getCepTwo(
        cep: String,
        result: Result<CepResponse, FailureError>.() -> Unit
    ) {
        callApi.safeCallApi(dispatcher) { cepService.getCepTwoAsync(cep).await() }
            .flow({
                result(Result.Success(it))
            }, {
                result(Result.Failure(it))
            })
    }

}