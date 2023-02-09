package com.zugzwang.services.consultingperformancemgmt.model

import org.springframework.data.annotation.Id
import java.util.UUID

data class Message(
    @Id val id: UUID,
    val msg: String
)
