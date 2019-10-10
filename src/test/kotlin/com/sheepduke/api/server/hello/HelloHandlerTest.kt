package com.sheepduke.api.server.hello

import com.sheepduke.api.server.common.ApiGatewayRequest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class HelloHandlerTest {
    @Test
    fun `test handleRequest`() {
        val request = ApiGatewayRequest(body = """
            {"name": "John"}
        """.trimIndent())
        val handler = HelloHandler()
        val response = handler.handleRequest(request)
        assertNotNull(response.body)
        assertEquals("""
            {"message":"Hello, John"}
        """.trimIndent(), response.bodyString)
    }
}