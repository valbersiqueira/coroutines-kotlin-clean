package com.teste.getcep.domain.usecase

import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.core.fuctions.flow
import com.teste.getcep.data.model.cep.toCep
import com.teste.getcep.data.model.error.FailureError
import com.teste.getcep.domain.entities.Cep
import com.teste.getcep.domain.repositories.CepRepository

class CepUseCase(private val cepRepository: CepRepository) {

    suspend fun getCep(
        cep: String,
        result: Result<Cep?, FailureError>.() -> Unit
    ) {
        cepRepository.getCep(cep) {
            flow({
                result(Result.Success(it?.toCep()))
            }, {
                result(Result.Failure(it))
            })
        }
    }

    suspend fun getCepTwo(
        cep: String,
        result: Result<Cep, FailureError>.() -> Unit
    ) {
        cepRepository.getCepTwo(cep) {
            flow({
                result(Result.Success(it.toCep()))
            }, {
                result(Result.Failure(it))
            })
        }
    }
}