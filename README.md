# coroutines-kotlin-clean
Projeto de exemplo com coroutines e kotlinx

# Teste no Repository
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