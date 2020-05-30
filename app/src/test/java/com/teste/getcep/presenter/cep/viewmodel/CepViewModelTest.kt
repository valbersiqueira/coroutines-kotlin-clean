package com.teste.getcep.presenter.cep.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.teste.getcep.MainCoroutineRule
import com.teste.getcep.domain.usecase.CepUseCase
import com.teste.getcep.mockCep
import com.teste.getcep.presenter.cep.state.CepState
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsInstanceOf
import org.junit.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class CepViewModelTest : AutoCloseKoinTest() {

    private val cepUseCase = Mockito.mock(CepUseCase::class.java)
    private lateinit var cepViewModel: CepViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
//        Dispatchers.setMain(dispatcher)
        startKoin {
            modules(module {
                factory { cepUseCase }
                viewModel { CepViewModel(get(), dispatcher) }
            })
        }
        cepViewModel = get()
    }

    @After
    fun tearDownDispatcher() {
//        Dispatchers.resetMain()
//        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Assert that call cep success`() {

        runBlockingTest {

            `when`(cepUseCase.getCepTwo("")).thenReturn(
                com.teste.getcep.core.fuctions.Result.Success(mockCep)
            )

            val expected = CepState.ShowCep::class.java

            cepViewModel.getCepTwo("")
            val actual = cepViewModel.cepState.value

            Assert.assertThat(actual, IsInstanceOf(expected))
        }

    }
}