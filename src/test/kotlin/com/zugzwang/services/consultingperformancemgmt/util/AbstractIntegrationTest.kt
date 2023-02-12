package com.zugzwang.services.consultingperformancemgmt.util

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest // Do integration tests with the service actually spun up
@AutoConfigureMockMvc // Use Spring DI to instantiate underlying MVC web framework object
@EnableConfigurationProperties // Apply configuration properties in test class
@Testcontainers // Not sure if this is necessary
abstract class AbstractIntegrationTest {
    companion object {
        // TODO: Match postgres image with what is used in deployment
        private val pgContainer = PostgreSQLContainer("postgres:latest").apply {
            withDatabaseName("postgres")
            withUsername("postgres")
            withPassword("postgres")
            withExposedPorts(5432)
            start()
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