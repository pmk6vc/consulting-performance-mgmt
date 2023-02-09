package com.zugzwang.services.consultingperformancemgmt.api

import com.zugzwang.services.consultingperformancemgmt.model.Message
import com.zugzwang.services.consultingperformancemgmt.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class HelloWorldController(private val messageService: MessageService) {

    @GetMapping("hello-world")
    fun getHelloWorld() = "Hello, world!"

    @GetMapping("sample-messages")
    fun getSampleMessages(): List<Message> = messageService.getSampleMessages()

}