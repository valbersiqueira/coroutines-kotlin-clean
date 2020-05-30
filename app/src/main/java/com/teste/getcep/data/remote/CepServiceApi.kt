package com.teste.getcep.data.remote

import com.teste.getcep.data.model.cep.CepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CepServiceApi {

    companion object {
        private const val PARAM_CEP = "cep"
        private const val GET_CEP = "{$PARAM_CEP}/json"
    }

    @GET(GET_CEP)
    suspend fun getCepAsync(@Path(PARAM_CEP) cep: String): CepResponse

    @GET(GET_CEP)
    suspend fun getCepTwoAsync(@Path(PARAM_CEP) cep: String): CepResponse

}