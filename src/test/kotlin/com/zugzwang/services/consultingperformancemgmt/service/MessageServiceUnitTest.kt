package com.zugzwang.services.consultingperformancemgmt.service

import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageCrudRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

private class MessageServiceTest {

    private val mockMessageCrudRepository = mockk<MessageCrudRepository>(relaxed = true)
    private val messageService = MessageService(mockMessageCrudRepository)

    @Test
    fun `should get messages from repository`() {
        messageService.getMessagesFromRepository()
        verify(exactly = 1) { mockMessageCrudRepository.list() }
    }
}