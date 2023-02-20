package com.zugzwang.services.consultingperformancemgmt.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("messages")
data class Message(
    @Id
    @GeneratedValue
    private val id: UUID,

    @Column(name = "message_content")
    val msg: String
)
