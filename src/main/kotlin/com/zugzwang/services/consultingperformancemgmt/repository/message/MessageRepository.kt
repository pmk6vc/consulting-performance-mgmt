package com.zugzwang.services.consultingperformancemgmt.repository.message

import com.zugzwang.services.consultingperformancemgmt.model.Message
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID


interface MessageRepository {

    fun getMessages(): List<Message>

}

// TODO: Not sure how this is instantiated by Spring DI
interface MessageCrudRepository: CrudRepository<Message, UUID> {

    // TODO: Don't hardcode table name
    // TODO: Using native queries locks you in to Postgres syntax - maybe use JPQL?
    @Query("SELECT * FROM messages")
    fun list(): List<Message>

}