package com.zugzwang.services.consultingperformancemgmt.controller

import com.zugzwang.services.consultingperformancemgmt.model.Message
import com.zugzwang.services.consultingperformancemgmt.service.MessageService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

const val MESSAGES_REQUEST_MAPPING_ROUTE = "messages"

@RestController
@RequestMapping(MESSAGES_REQUEST_MAPPING_ROUTE)
class MessageController(private val messageService: MessageService) {

    // Handle server exceptions of passed type by returning dedicated ResponseEntity instead
    // Handler scoped to this controller - other controllers can handle same exception differently
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun getMessages(): Iterable<Message> = messageService.getAllMessages()

    @GetMapping("{messageId}")
    fun getMessageById(@PathVariable messageId: String) = messageService.getMessageById(UUID.fromString(messageId))

    @GetMapping("{messageId}/optional")
    fun getOptionalMessageById(@PathVariable messageId: String) = messageService.getOptionalMessageById(UUID.fromString(messageId))

    @PostMapping
    fun postMessage(@RequestBody message: Message) = messageService.post(message)

}