package com.zugzwang.services.consultingperformancemgmt.repository.message

import com.zugzwang.services.consultingperformancemgmt.model.Message

// TODO: Extend MessageRepository with CrudRepository<Message, UUID> for CRUD operation support
interface MessageRepository {

    fun getMessages(): List<Message>

}