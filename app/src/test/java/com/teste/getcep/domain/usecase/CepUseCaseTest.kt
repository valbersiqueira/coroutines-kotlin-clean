package com.teste.getcep.domain.usecase

import com.teste.getcep.argumentCaptor
import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.core.fuctions.flow
import com.teste.getcep.data.model.cep.CepResponse
import com.teste.getcep.data.model.error.FailureError
import com.teste.getcep.domain.entities.Cep
import com.teste.getcep.domain.repositories.CepRepository
import com.teste.getcep.mockCepResponse
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class CepUseCaseTest {

    private val cepRepository = mock(CepRepository::class.java)
    private lateinit var cepUseCaseTest: CepUseCase

    @Before
    fun setUp() {
        cepUseCaseTest = CepUseCase(cepRepository)
    }

    @Test
    fun `Assert that cep two no-null receive usecase`() {
        val resultCaptor = argumentCaptor<Result<CepResponse?, FailureError>.() -> Unit>()
        val cepCaptor = argumentCaptor<String>()
        runBlockingTest {

            Mockito.doAnswer {
                @Suppress("UNCHECKED_CAST")
                (it.arguments[1] as Result<CepResponse, FailureError.FailureServer>.() -> Unit)
                    .invoke(Result.Success(mockCepResponse))
            }.`when`(cepRepository).getCepTwo(
                cepCaptor.capture(),
                resultCaptor.captureLambda()
            )

            var cep: Cep? = null
            cepUseCaseTest.getCepTwo("") {
                flow({
                    cep = it
                }, {})
            }
            Assert.assertNotNull(cep)
        }
    }

    @Test
    fun `Assert that cep no-null receive usecase`() {
        val resultCaptor = argumentCaptor<Result<CepResponse?, FailureError>.() -> Unit>()
        val cepCaptor = argumentCaptor<String>()
        runBlockingTest {

            Mockito.doAnswer {
                @Suppress("UNCHECKED_CAST")
                (it.arguments[1] as Result<CepResponse, FailureError.FailureServer>.() -> Unit)
                    .invoke(Result.Success(mockCepResponse))
            }.`when`(cepRepository).getCep(
                cepCaptor.capture(),
                resultCaptor.captureLambda()
            )

            var cep: Cep? = null
            cepUseCaseTest.getCep("") {
                flow({
                    cep = it
                }, {})
            }
            Assert.assertNotNull(cep)
        }
    }
}