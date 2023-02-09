package com.zugzwang.services.consultingperformancemgmt.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class HelloWorldController {

    data class Message(val msg: String)

    @GetMapping("hello-world")
    fun getHelloWorld() = "Hello, world!"

    @GetMapping("hello-world-messages")
    fun getHelloWorldMessages(): List<Message> {
        return listOf(
            Message("Hello"),
            Message("World")
        )
    }

}