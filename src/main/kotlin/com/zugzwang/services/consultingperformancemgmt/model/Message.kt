package com.zugzwang.services.consultingperformancemgmt.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

class MessageModel {
    companion object {

        const val MESSAGES_TABLE = "messages"

        @Table(MESSAGES_TABLE)
        data class Message(
            @Id val id: UUID? = null,
            @Column("message_content") val msg: String
        )

    }
}
