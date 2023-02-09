package com.zugzwang.services.consultingperformancemgmt.service

import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

private class MessageServiceTest {

    private val mockMessageRepository = mockk<MessageRepository>()
    private val messageService = MessageService(mockMessageRepository)

    @Test
    fun `should get messages from repository`() {
        every { mockMessageRepository.getMessages() } returns emptyList()
        val messages = messageService.getMessagesFromSource()
        verify(exactly = 1) { mockMessageRepository.getMessages() }
    }
}