package com.zugzwang.services.consultingperformancemgmt.repository.message

import com.zugzwang.services.consultingperformancemgmt.model.Message
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID


interface MessageCrudRepository: CrudRepository<Message, UUID> {

    // TODO: Don't hardcode table name
    @Query("SELECT * FROM messages")
    fun list(): List<Message>

}