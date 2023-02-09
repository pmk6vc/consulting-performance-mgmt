package com.zugzwang.services.consultingperformancemgmt.repository.message

import com.zugzwang.services.consultingperformancemgmt.model.Message
import org.springframework.stereotype.Repository
import java.util.*

@Repository("alternative")
class AlternativeMessageRepository: MessageRepository {

    override fun getMessages(): List<Message> {
        return listOf(
            Message(UUID.randomUUID(), "Goodbye"),
            Message(UUID.randomUUID(), "World")
        )
    }

}