package com.zugzwang.services.consultingperformancemgmt.repository.message

import com.zugzwang.services.consultingperformancemgmt.model.Message
import org.springframework.stereotype.Repository
import java.util.*

@Repository("basic")
class BasicMessageRepository : MessageRepository {

    override fun getMessages(): List<Message> {
        return listOf(
            Message(UUID.randomUUID(), "Hello"),
            Message(UUID.randomUUID(), "World")
        )
    }

}