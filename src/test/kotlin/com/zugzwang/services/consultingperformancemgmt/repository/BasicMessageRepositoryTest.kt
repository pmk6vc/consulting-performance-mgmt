package com.zugzwang.services.consultingperformancemgmt.repository

import com.zugzwang.services.consultingperformancemgmt.repository.message.BasicMessageRepository
import org.junit.jupiter.api.Test

private class BasicMessageRepositoryTest {

    private val basicMessageRepository = BasicMessageRepository()

    @Test
    fun `should get sample messages`() {
        val messages = basicMessageRepository.getSampleMessages()
        assert(messages.isNotEmpty())
    }

    @Test
    fun `sample messages should be valid`() {
        val messages = basicMessageRepository.getSampleMessages()
        assert(messages.none { it.msg.isEmpty() })
    }
}