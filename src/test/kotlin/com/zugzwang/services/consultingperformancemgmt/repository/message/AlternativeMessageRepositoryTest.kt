package com.zugzwang.services.consultingperformancemgmt.repository.message

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

private class AlternativeMessageRepositoryTest {

    // Note: This instantiation means that this test suite does not test any Spring-related DI
    // In other words, this is the standard approach for unit testing
    private val alternativeMessageRepository = AlternativeMessageRepository()

    @Test
    fun `should get expected messages`() {
        val messages = alternativeMessageRepository.getMessages()
        assertEquals(setOf("Goodbye", "World"), messages.map { it.msg }.toSet())
    }
}