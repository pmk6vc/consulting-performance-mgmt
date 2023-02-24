package com.zugzwang.services.consultingperformancemgmt.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.zugzwang.services.consultingperformancemgmt.model.Message
import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageCrudRepository
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

private class MessageControllerIntegrationTest : AbstractIntegrationTest() {

    @Autowired // Use Spring DI to create MVC
    private lateinit var mockMvc: MockMvc // Enable hitting controller without actually making HTTP requests

    @Autowired
    private lateinit var messageRepository: MessageCrudRepository

    private val mapper = jacksonObjectMapper()

    @Nested
    @DisplayName("Get messages")
    inner class GetMessages {

        @Test
        fun `should get all messages`() {
            val expectedJsonList = messageRepository.findAll().map { mapper.writeValueAsString(it) }
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
                            value(expectedJsonList.size)
                        }
                        json(expectedJsonList.toString())
                    }
                }
        }

        @Test
        fun `should get specific message`() {
            val expectedMessage = messageRepository.findAll().first()
            val expectedJson = mapper.writeValueAsString(expectedMessage)
            val response = mockMvc
                .get("/messages/messages-from-service/${expectedMessage.id}")
                .andDo { print() }
                .andExpectAll {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(expectedJson)
                    }
                }
                .andReturn()
            val actualMessage: Message = jacksonObjectMapper().readValue(response.response.contentAsString)
            assertEquals(expectedMessage, actualMessage)
        }

    }

    @Test
    fun `should post message to database`() {
        mockMvc
            .post("/messages/post-message")
    }
}