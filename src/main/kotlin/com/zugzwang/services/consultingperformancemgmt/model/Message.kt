package com.zugzwang.services.consultingperformancemgmt.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("messages")
data class Message(
    @Id private val id: UUID,
    // TODO: See if you can use annotations to map this to a different column name
    val msg: String
)
