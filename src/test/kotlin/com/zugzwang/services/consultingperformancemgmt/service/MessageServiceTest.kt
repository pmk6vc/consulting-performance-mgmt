package com.zugzwang.services.consultingperformancemgmt.service

import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

private class MessageServiceTest {

    private val mockMessageRepository = mockk<MessageRepository>(relaxed = true)
    private val messageService = MessageService(mockMessageRepository)

    @Test
    fun `should get messages from repository`() {
        messageService.getMessagesFromRepository()
        verify(exactly = 1) { mockMessageRepository.getMessages() }
    }
}