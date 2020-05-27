package com.teste.getcep.domain.repositories

import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.data.model.cep.CepResponse
import com.teste.getcep.data.model.error.FailureError

interface CepRepository {

   suspend fun getCep(
        cep: String,
        result: Result<CepResponse?, FailureError>.() -> Unit
    )

    suspend fun getCepTwo(
        cep: String,
        result: Result<CepResponse, FailureError>.() -> Unit
    )
}