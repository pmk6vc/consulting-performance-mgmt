package com.zugzwang.services.consultingperformancemgmt.controller

import com.zugzwang.services.consultingperformancemgmt.util.AbstractIntegrationTest
import io.mockk.InternalPlatformDsl.toStr
import kotlinx.serialization.json.*
import org.hamcrest.Matchers
import org.json.JSONObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

private class MessageControllerIntegrationTest : AbstractIntegrationTest() {

    @Autowired // Use Spring DI to create MVC
    private lateinit var mockMvc: MockMvc // Enable hitting controller without actually making HTTP requests

    @Value("\${spring.datasource.driver-class-name}")
    lateinit var datasourceDriver: String

    @Value("\${server.port}")
    lateinit var serverPort: String

    @Test
    fun `should apply configured properties`() {
        // Note: Production properties in classPath still seem to apply in test if not overridden
        assertEquals("org.postgresql.Driver", datasourceDriver)
        assertEquals("8001", serverPort)
    }

    @Test
    fun `should get sample hello world message`() {
        mockMvc
            .get("/messages/hello-world-message")
            .andExpect { jsonPath("$") {} }
            .andDo { print() }
            .andExpectAll {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    jsonPath("$.msg") {
                        value("Hello, world!")
                    }
                }
            }
    }

    @Test
    fun `should get messages from database`() {
        val expectedJson = buildJsonArray {
            addJsonObject { put("msg", "Hello from Liquibase!") }
            addJsonObject { put("msg", "Goodbye from Liquibase!") }
        }
        mockMvc
            .get("/messages/messages-from-service")
            .andDo { print() }
            .andExpectAll {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    jsonPath("$") {
                        isArray()
                    }
                    jsonPath("$.length()") {
                        value(2)
                    }
                    json(expectedJson.toString())
                }
            }
    }
}