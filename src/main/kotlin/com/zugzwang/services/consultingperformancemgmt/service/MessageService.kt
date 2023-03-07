package com.zugzwang.services.consultingperformancemgmt.service

import com.zugzwang.services.consultingperformancemgmt.model.Message
import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageCrudRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService(private val messageRepository: MessageCrudRepository) {

    fun getAllMessages(): Iterable<Message> = messageRepository.findAll()

    fun getMessageById(messageId: UUID): Message = messageRepository
        .findById(messageId)
        .orElseThrow { NoSuchElementException("No message with ID $messageId found!") }

    fun getOptionalMessageById(messageId: UUID): Optional<Message> = messageRepository.findById(messageId)

    fun getMessagesByContent(content: String): Iterable<Message> = messageRepository.findAllByMsg(content)

    fun post(message: Message) = messageRepository.save(message)

}