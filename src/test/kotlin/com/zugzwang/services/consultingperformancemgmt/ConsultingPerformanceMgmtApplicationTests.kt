package com.zugzwang.services.consultingperformancemgmt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.zugzwang.services.consultingperformancemgmt.controller.MainController
import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageCrudRepository
import com.zugzwang.services.consultingperformancemgmt.util.AbstractIntegrationTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.nio.charset.StandardCharsets

class ConsultingPerformanceMgmtApplicationTests : AbstractIntegrationTest() {

	@Autowired // Use Spring DI to create MVC
	private lateinit var mockMvc: MockMvc // Enable hitting controller without actually making HTTP requests

	@Autowired
	private lateinit var messageRepository: MessageCrudRepository

	@Nested
	@DisplayName("Spring config")
	inner class SpringConfig {

		@Value("\${spring.datasource.driver-class-name}")
		lateinit var datasourceDriver: String

		@Value("\${server.port}")
		lateinit var serverPort: String

		@Test
		fun contextLoads() {
		}

		@Test
		fun `should apply configured properties`() {
			// Note: Production properties in classPath still seem to apply in test if not overridden
			assertEquals("org.postgresql.Driver", datasourceDriver)
			assertEquals("8001", serverPort)
		}

		@Test
		fun `should run liquibase migrations and seed with initial data`() {
			val expectedMessages = setOf(
				"Hello from Liquibase!",
				"Goodbye from Liquibase!"
			)
			val actualMessages = messageRepository.findAll().map { it.msg }.toSet()
			assertEquals(expectedMessages, actualMessages)
		}

	}

	@Nested
	@DisplayName("Health checks")
	inner class HealthChecks {

		private val mapper = jacksonObjectMapper()

		@Test
		fun `should ping`() {
			mockMvc
				.get("/_ping")
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
			val obj = MainController.Companion.Sample(data)
			val expectedEncoded = buildJsonObject {
				put("data", data)
			}
			val response = mockMvc
				.get("/_ping/$data")
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
			val decoded: MainController.Companion.Sample = mapper.readValue(response.response.contentAsString)
			assertEquals(obj, decoded)
		}

	}
}
