package com.zugzwang.services.consultingperformancemgmt.service

import com.zugzwang.services.consultingperformancemgmt.model.Message
import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageCrudRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MessageService(private val messageRepository: MessageCrudRepository) {

    fun getAllMessages(): Iterable<Message> = messageRepository.findAll()

    fun getMessageById(messageId: UUID) = messageRepository.findById(messageId)

    fun getMessagesByContent(content: String): Iterable<Message> = messageRepository.findAllByMsg(content)

    fun post(message: Message) = messageRepository.save(message)

}