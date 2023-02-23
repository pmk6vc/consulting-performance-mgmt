package com.zugzwang.services.consultingperformancemgmt.controller

import com.zugzwang.services.consultingperformancemgmt.util.AbstractIntegrationTest
import kotlinx.serialization.json.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.nio.charset.StandardCharsets

private class MessageControllerIntegrationTest : AbstractIntegrationTest() {

    @Autowired // Use Spring DI to create MVC
    private lateinit var mockMvc: MockMvc // Enable hitting controller without actually making HTTP requests

    @Nested
    @DisplayName("Get messages")
    inner class GetMessages {

        @Test
        fun `should get initial messages from database`() {
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

        @Test
        fun `should get specific message`() {
            val messageId = 1234
            mockMvc
                .get("/messages/messages-from-service/$messageId")
                .andDo { print() }
                .andExpectAll {
                    status { isOk() }
                    content {
                        contentType(MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8))
                        string("Get message $messageId")
                    }
                }
        }

    }



    @Test
    fun `should post message to database`() {
        mockMvc
            .post("/messages/post-message")
    }
}