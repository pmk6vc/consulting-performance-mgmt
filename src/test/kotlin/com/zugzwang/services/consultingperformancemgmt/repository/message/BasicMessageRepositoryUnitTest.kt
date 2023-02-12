package com.zugzwang.services.consultingperformancemgmt.repository.message

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

private class BasicMessageRepositoryTest {

    // Note: This instantiation means that this test suite does not test any Spring-related DI
    // In other words, this is the standard approach for unit testing
    private val basicMessageRepository = BasicMessageRepository()

    @Test
    fun `should get expected messages`() {
        val messages = basicMessageRepository.getMessages()
        assertEquals(setOf("Hello", "World"), messages.map { it.msg }.toSet())
    }
}