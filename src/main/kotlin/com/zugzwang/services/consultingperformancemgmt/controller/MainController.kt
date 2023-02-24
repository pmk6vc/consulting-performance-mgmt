package com.zugzwang.services.consultingperformancemgmt.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    companion object {
        data class Sample(
            val data: Int
        )
    }

    @GetMapping("_ping")
    fun ping() = "Ping! I'm healthy!"

    @GetMapping("_ping/{data}")
    fun pingJson(@PathVariable data: Int) = Sample(data)

}