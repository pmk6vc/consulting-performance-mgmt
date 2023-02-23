package com.zugzwang.services.consultingperformancemgmt.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @GetMapping("_ping")
    fun ping() = "Ping! I'm healthy!"

}