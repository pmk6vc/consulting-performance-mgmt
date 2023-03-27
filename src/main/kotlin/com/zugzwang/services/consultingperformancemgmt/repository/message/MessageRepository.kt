package com.zugzwang.services.consultingperformancemgmt.repository.message

import com.zugzwang.services.consultingperformancemgmt.model.MessageModel
import com.zugzwang.services.consultingperformancemgmt.model.MessageModel.Companion.Message
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.UUID


interface MessageCrudRepository: CrudRepository<Message, UUID> {

    @Query("SELECT * FROM ${MessageModel.MESSAGES_TABLE} WHERE msg = :msg")
    fun findAllByMsg(@Param("msg") messageContent: String): Iterable<Message>

}