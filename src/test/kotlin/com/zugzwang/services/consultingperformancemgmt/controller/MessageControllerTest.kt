package com.zugzwang.services.consultingperformancemgmt.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest // Do integration tests with the service actually spun up
@AutoConfigureMockMvc // Use Spring DI to instantiate underlying MVC web framework object
private class MessageControllerTest {

    @Autowired // Use Spring DI to create MVC
    lateinit var mockMvc: MockMvc // Enable hitting controller without actually making HTTP requests

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