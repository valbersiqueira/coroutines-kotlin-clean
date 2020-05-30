package com.teste.getcep

import com.teste.getcep.core.fuctions.Result
import com.teste.getcep.core.request.CallApi
import com.teste.getcep.core.request.CallApiImpl
import com.teste.getcep.core.request.OnRequest
import com.teste.getcep.data.remote.CepServiceApi
import com.teste.getcep.data.repositories.CepRepositoryImpl
import com.teste.getcep.domain.repositories.CepRepository
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveTheSameClassAs
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

@Suppress("DeferredResultUnused")
class CepRepositoryTest {

    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var callApi: CallApi

    private val callApiMock = mock(CallApi::class.java)

    private val cepServiceApi = mock(CepServiceApi::class.java)
    private val onRequest = mock(OnRequest::class.java)

    private lateinit var cepRepository: CepRepository


    @Before
    fun setUp() {
        callApi = CallApiImpl()
        cepRepository = CepRepositoryImpl(onRequest, cepServiceApi, dispatcher, callApi)
    }


    @Test
    fun `Assert that call api cep success`() {
        runBlockingTest {
            val lambdaRequest = mockCepResponse
            val result = callApi.safeCallApi(dispatcher) { lambdaRequest }
            assertEquals(Result.Success(lambdaRequest), result)
        }
    }

    @Test
    fun `Assert that call cep two repository success`() {
        runBlockingTest {
            `when`(cepServiceApi.getCepTwoAsync("")).thenReturn(mockCepResponse)

            val expected = Result.Success(mockCepResponse)
            val actual = cepRepository.getCepTwo("")

            actual shouldHaveTheSameClassAs  Result.Success(mockCepResponse)

            actual shouldBeEqualTo expected

            verify(cepServiceApi).getCepTwoAsync("")
            verifyNoMoreInteractions(cepServiceApi)
        }
    }

    @Test
    fun `Assert that call cep repository success`() {
        runBlockingTest {

            cepRepository.getCep("") { }

            verify(cepServiceApi).getCepAsync("")
            verifyNoMoreInteractions(cepServiceApi)
        }
    }

}

