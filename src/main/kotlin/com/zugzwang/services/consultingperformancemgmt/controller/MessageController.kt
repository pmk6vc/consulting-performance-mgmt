package com.zugzwang.services.consultingperformancemgmt.controller

import com.zugzwang.services.consultingperformancemgmt.model.Message
import com.zugzwang.services.consultingperformancemgmt.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

const val REQUEST_MAPPING_ROUTE = "messages"

@RestController
@RequestMapping(REQUEST_MAPPING_ROUTE)
class MessageController(private val messageService: MessageService) {

    @GetMapping("messages-from-service")
    fun getMessages(): Iterable<Message> = messageService.getAllMessages()

    // TODO: Add test coverage for this method
    @PostMapping("post-messages")
    fun postMessage(@RequestBody message: Message) = messageService.post(message)

    @GetMapping("messages-from-service/{messageId}")
    fun getMessageById(@PathVariable messageId: String) = messageService.getMessageById(UUID.fromString(messageId))

}