package com.zugzwang.services.consultingperformancemgmt.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val HEALTH_CHECKS_REQUEST_MAPPING_ROUTE = "_health"

@RestController
@RequestMapping(HEALTH_CHECKS_REQUEST_MAPPING_ROUTE)
class HealthChecksController {

    companion object {
        data class Sample(
            val data: Int
        )
    }

    @GetMapping
    fun ping() = "Ping! I'm healthy!"

    @GetMapping("{data}")
    fun pingJson(@PathVariable data: Int) = Sample(data)

}