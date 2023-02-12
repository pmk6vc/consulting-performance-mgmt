package com.zugzwang.services.consultingperformancemgmt.controller

import com.zugzwang.services.consultingperformancemgmt.util.AbstractIntegrationTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

private class MessageControllerTest : AbstractIntegrationTest() {

    @Autowired // Use Spring DI to create MVC
    private lateinit var mockMvc: MockMvc // Enable hitting controller without actually making HTTP requests

    @Value("\${server.port}")
    lateinit var serverPort: String

    @Value("\${spring.sql.init.mode}")
    lateinit var sqlInitMode: String

    @Test
    fun `should apply configured properties`() {
        // Note: Production properties in classPath still seem to apply in test if not overridden
        assertEquals("8000", serverPort)
        assertEquals("always", sqlInitMode)
    }

    @Test
    fun `should get sample hello world message`() {
        mockMvc
            .get("/messages/hello-world-message")
            .andDo { print() }
            .andExpectAll {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    jsonPath("$.msg") { value("Hello, world!") }
                }
            }
    }
}