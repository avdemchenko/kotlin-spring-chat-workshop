package com.example.kotlin.chat.service

import asDomainObject
import asViewModel
import com.example.kotlin.chat.repository.ContentType
import com.example.kotlin.chat.repository.ContentType.PLAIN
import com.example.kotlin.chat.repository.Message
import com.example.kotlin.chat.repository.MessageRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.net.URL

@Service
@Primary
class PersistentMessageService(val messageRepository: MessageRepository) : MessageService {

    override fun latest(): List<MessageVM> =
            messageRepository.findLatest().map { it.asViewModel() }

    override fun after(lastMessageId: String): List<MessageVM> =
            messageRepository.findLatest(lastMessageId).map { it.asViewModel() }

    override fun post(message: MessageVM) { messageRepository.save(message.asDomainObject()) }
}
