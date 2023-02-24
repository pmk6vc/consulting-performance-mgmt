package com.zugzwang.services.consultingperformancemgmt.service

import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageCrudRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

private class MessageServiceUnitTest {

    private val mockMessageCrudRepository = mockk<MessageCrudRepository>(relaxed = true)
    private val messageService = MessageService(mockMessageCrudRepository)

    @Test
    fun `should get messages from repository`() {
        messageService.getAllMessages()
        verify(exactly = 1) { mockMessageCrudRepository.findAll() }
    }
}