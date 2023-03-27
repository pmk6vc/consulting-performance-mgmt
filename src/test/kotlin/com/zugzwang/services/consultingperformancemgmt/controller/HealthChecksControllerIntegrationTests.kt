package com.zugzwang.services.consultingperformancemgmt.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.zugzwang.services.consultingperformancemgmt.util.AbstractIntegrationTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.nio.charset.StandardCharsets

class HealthChecksControllerIntegrationTests @Autowired constructor(
    private val mockMvc: MockMvc,
    private val mapper: ObjectMapper
) : AbstractIntegrationTest() {

    @Nested
    @DisplayName("Health checks")
    inner class HealthChecks {

        private val baseRoute = "/$HEALTH_CHECKS_REQUEST_MAPPING_ROUTE"

        @Test
        fun `should ping`() {
            mockMvc
                .get(baseRoute)
                .andDo { print() }
                .andExpectAll {
                    status { isOk() }
                    content {
                        contentType(MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8))
                        string("Ping! I'm healthy!")
                    }
                }
        }

        @Test
        fun `should decode JSON response to data class predictably`() {
            val data = 10
            val obj = HealthChecksController.Companion.Sample(data)
            val expectedEncoded = buildJsonObject {
                put("data", data)
            }
            val response = mockMvc
                .get("$baseRoute/$data")
                .andDo { print() }
                .andExpectAll {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(mapper.writeValueAsString(obj))
                        json(expectedEncoded.toString())
                    }
                }
                .andReturn()
            val decoded: HealthChecksController.Companion.Sample = mapper.readValue(response.response.contentAsString)
            Assertions.assertEquals(obj, decoded)
        }

    }

}