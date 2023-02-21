package com.zugzwang.services.consultingperformancemgmt.service

import com.zugzwang.services.consultingperformancemgmt.model.Message
import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageCrudRepository
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageRepository: MessageCrudRepository) {

    fun getMessagesFromRepository() = messageRepository.list()

    fun post(message: Message) = messageRepository.save(message)

}