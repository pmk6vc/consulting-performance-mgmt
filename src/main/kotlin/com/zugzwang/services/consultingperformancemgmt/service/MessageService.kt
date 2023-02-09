package com.zugzwang.services.consultingperformancemgmt.service

import com.zugzwang.services.consultingperformancemgmt.repository.message.MessageRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class MessageService(@Qualifier("basic") private val messageRepository: MessageRepository) {

    fun getMessagesFromRepository() = messageRepository.getMessages()

}