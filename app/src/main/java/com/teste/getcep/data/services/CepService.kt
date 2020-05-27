package com.teste.getcep.data.services

import com.teste.getcep.data.remote.CepServiceApi
import retrofit2.Retrofit

class CepService(private val retrofit: Retrofit) : CepServiceApi {

    private val cepServiceApi by lazy { retrofit.create(CepServiceApi::class.java) }

    override fun getCepAsync(cep: String) = cepServiceApi.getCepAsync(cep)

    override fun getCepTwoAsync(cep: String) = cepServiceApi.getCepTwoAsync(cep)
}