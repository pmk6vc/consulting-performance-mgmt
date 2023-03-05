package com.zugzwang.services.consultingperformancemgmt.util

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest // Perform integration tests with the service actually spun up
@AutoConfigureMockMvc // Use Spring DI to instantiate underlying MVC web framework object
@TestPropertySource(locations = ["classpath:application-test.properties"]) // Point to test overrides
@Testcontainers // Manages container lifecycle across tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Indicate that each test requires resetting context to avoid side effects
abstract class AbstractIntegrationTest {
    companion object {
        // TODO: Match postgres image with what is used in deployment
        @Container // Annotate containers to manage lifecycle across tests
        private val pgContainer = PostgreSQLContainer("postgres:14-alpine").apply {
            withDatabaseName("postgres")
            withUsername("postgres")
            withPassword("postgres")
            withExposedPorts(5432)
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", pgContainer::getJdbcUrl)
            registry.add("spring.datasource.username", pgContainer::getUsername)
            registry.add("spring.datasource.password", pgContainer::getPassword)
        }
    }
}