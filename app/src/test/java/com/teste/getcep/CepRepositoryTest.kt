package com.teste.getcep

import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.core.fuctions.flow
import com.teste.getcep.core.request.CallApi
import com.teste.getcep.core.request.CallApiImpl
import com.teste.getcep.core.request.OnRequest
import com.teste.getcep.data.model.cep.CepResponse
import com.teste.getcep.data.model.error.FailureError
import com.teste.getcep.data.remote.CepServiceApi
import com.teste.getcep.data.repositories.CepRepositoryImpl
import com.teste.getcep.domain.entities.Cep
import com.teste.getcep.domain.repositories.CepRepository
import com.teste.getcep.domain.usecase.CepUseCase
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

@Suppress("DeferredResultUnused")
class CepRepositoryTest {

    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var callApi: CallApi

    private val cepServiceApi = mock(CepServiceApi::class.java)
    private val onRequest = mock(OnRequest::class.java)
    private val cepRepositoryMock = mock(CepRepository::class.java)
    private lateinit var cepUseCase: CepUseCase

    private lateinit var cepRepository: CepRepository


    @Before
    fun setUp() {
        callApi = CallApiImpl()
        cepRepository = CepRepositoryImpl(onRequest, cepServiceApi, dispatcher, callApi)
        cepUseCase = CepUseCase(cepRepositoryMock)
    }

    @Test
    fun `Assert that cep no-null receive usecase`() {
        val resultCaptor = argumentCaptor<Result<CepResponse?, FailureError>.() -> Unit>()
        val cepCaptor = argumentCaptor<String>()
        runBlockingTest {

            doAnswer {
                @Suppress("UNCHECKED_CAST")
                (it.arguments[1] as Result<CepResponse, FailureError.FailureServer>.() -> Unit)
                    .invoke(Result.Success(mockCepResponse))
            }.`when`(cepRepositoryMock).getCep(
                cepCaptor.capture(),
                resultCaptor.captureLambda()
            )

            var cep: Cep? = null
            cepUseCase.getCep("") {
                flow({
                    cep = it
                }, {})
            }
            Assert.assertNotNull(cep)
        }
    }

    @Test
    fun `Assert that call api cep async`() {
        cepServiceApi.getCepAsync("")
        verify(cepServiceApi).getCepAsync("")
    }

    @Test
    fun `Assert that call api cep two async`() {
        cepServiceApi.getCepTwoAsync("")
        verify(cepServiceApi).getCepTwoAsync("")
    }

    @Test
    fun `Assert that call api cep`() {
        runBlockingTest {
            val lambdaRequest = mockCepResponse
            val result = callApi.safeCallApi(dispatcher) { lambdaRequest }
            assertEquals(Result.Success(lambdaRequest), result)
        }
    }
}

