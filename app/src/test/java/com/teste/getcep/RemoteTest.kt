package com.teste.getcep

import com.teste.getcep.core.retrofit
import org.junit.Test
import kotlin.test.assertNotNull

class RemoteTest {

    @Test
    fun `Assert that retrofit no-null`() {
        val retrofit = retrofit("https://viacep.com.br/ws/")
        assertNotNull(retrofit)
    }
}