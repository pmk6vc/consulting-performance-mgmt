package com.zugzwang.services.consultingperformancemgmt

import com.zugzwang.services.consultingperformancemgmt.util.AbstractIntegrationTest
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.nio.charset.StandardCharsets

class ConsultingPerformanceMgmtApplicationTests : AbstractIntegrationTest() {

	@Autowired // Use Spring DI to create MVC
	private lateinit var mockMvc: MockMvc // Enable hitting controller without actually making HTTP requests

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
			Assertions.assertEquals("org.postgresql.Driver", datasourceDriver)
			Assertions.assertEquals("8001", serverPort)
		}

	}

	@Nested
	@DisplayName("Health checks")
	inner class HealthChecks {

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

	}
}
