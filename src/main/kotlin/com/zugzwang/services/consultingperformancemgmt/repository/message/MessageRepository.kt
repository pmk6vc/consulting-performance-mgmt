package com.zugzwang.services.consultingperformancemgmt.repository.message

import com.zugzwang.services.consultingperformancemgmt.model.Message
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.UUID


interface MessageCrudRepository: CrudRepository<Message, UUID> {

    // TODO: Don't hardcode table name
    @Query("SELECT * FROM messages WHERE msg = :msg")
    fun findAllByMsg(@Param("msg") messageContent: String): Iterable<Message>

}