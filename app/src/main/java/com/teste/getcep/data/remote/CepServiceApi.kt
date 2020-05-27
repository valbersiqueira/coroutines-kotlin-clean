package com.teste.getcep.data.remote

import com.teste.getcep.data.model.cep.CepResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface CepServiceApi {

    companion object {
        private const val PARAM_CEP = "cep"
        private const val GET_CEP = "{$PARAM_CEP}/json"
    }

    @GET(GET_CEP)
    fun getCepAsync(@Path(PARAM_CEP) cep: String): Deferred<CepResponse>

    @GET(GET_CEP)
    fun getCepTwoAsync(@Path(PARAM_CEP) cep: String): Deferred<CepResponse>

}