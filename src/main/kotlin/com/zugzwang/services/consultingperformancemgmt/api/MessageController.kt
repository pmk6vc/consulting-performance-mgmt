package com.zugzwang.services.consultingperformancemgmt.api

import com.zugzwang.services.consultingperformancemgmt.model.Message
import com.zugzwang.services.consultingperformancemgmt.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("messages")
class MessageController(private val messageService: MessageService) {

    @GetMapping("hello-world-message")
    fun getHelloWorld() = Message(UUID.randomUUID(), "Hello, world!")

    @GetMapping("messages-from-service")
    fun getMessagesFromService(): List<Message> = messageService.getMessagesFromSource()

}