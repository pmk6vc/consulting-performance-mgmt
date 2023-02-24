package com.zugzwang.services.consultingperformancemgmt.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("messages")
data class Message(
    @Id val id: UUID,
    @Column("message_content") val msg: String
)
