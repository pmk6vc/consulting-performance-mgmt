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
import java.util.UUID

private class MessageControllerIntegrationTest : AbstractIntegrationTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var messageRepository: MessageCrudRepository

    private val mapper = jacksonObjectMapper()
    private val baseRoute = "/$MESSAGES_REQUEST_MAPPING_ROUTE"

    @Nested
    @DisplayName("Get messages")
    inner class GetMessages {

        @Test
        fun `should get all messages`() {
            val expectedJsonList = messageRepository.findAll().map { mapper.writeValueAsString(it) }
            mockMvc
                .get(baseRoute)
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
            // TODO: Try to refactor test so that it doesn't depend on any seeded value
            val expectedMessage = messageRepository.findAll().first()
            val response = mockMvc
                .get("$baseRoute/${expectedMessage.id}")
                .andDo { print() }
                .andExpectAll {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(mapper.writeValueAsString(expectedMessage))
                    }
                }
                .andReturn()
            val actualMessage: Message = mapper.readValue(response.response.contentAsString)
            assertEquals(expectedMessage, actualMessage)
        }

        @Test
        fun `should get handled exception when searching for nonexistent message`() {
            val missingMessageId = UUID.randomUUID()
            mockMvc
                .get("$baseRoute/$missingMessageId")
                .andDo { print() }
                .andExpectAll {
                    status { isNotFound() }
                    content {
                        string("No message with ID $missingMessageId found!")
                    }
                }
        }

        @Test
        fun `should get empty response when searching for nonexistent message on optional route`() {
            mockMvc
                .get("$baseRoute/${UUID.randomUUID()}/optional")
                .andDo { print() }
                .andExpectAll {
                    status { isOk() }
                    content {
                        jsonPath("$") {
                            doesNotExist()
                        }
                    }
                }
        }

    }

    @Nested
    @DisplayName("Post message")
    inner class PostMessage {

        @Test
        fun `should post message to database`() {
            val postMessage = Message(msg = "H E L L O")
            val response = mockMvc
                .post(baseRoute) {
                    contentType = MediaType.APPLICATION_JSON
                    content = mapper.writeValueAsString(postMessage)
                    accept = MediaType.APPLICATION_JSON
                }
                .andDo { print() }
                .andExpectAll {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        jsonPath("$.msg") {
                            value(postMessage.msg)
                        }
                    }
                }
                .andReturn()
            val postedMessage: Message = mapper.readValue(response.response.contentAsString)
            val messageFromDb = messageRepository.findById(postedMessage.id!!).get()
            assertEquals(postMessage.msg, postedMessage.msg)
            assertEquals(postedMessage, messageFromDb)
        }

    }
}