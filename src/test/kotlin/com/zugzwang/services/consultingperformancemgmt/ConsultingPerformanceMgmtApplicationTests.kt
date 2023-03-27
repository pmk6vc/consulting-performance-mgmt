package com.zugzwang.services.consultingperformancemgmt

import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageCrudRepository
import com.zugzwang.services.consultingperformancemgmt.util.AbstractIntegrationTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

class ConsultingPerformanceMgmtApplicationTests @Autowired constructor(
	private val messageRepository: MessageCrudRepository,
	@Value("\${spring.datasource.driver-class-name}") private val datasourceDriver: String,
	@Value("\${server.port}") private val serverPort: String
) : AbstractIntegrationTest() {

	@Nested
	@DisplayName("Spring config")
	inner class SpringConfig {

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
}
