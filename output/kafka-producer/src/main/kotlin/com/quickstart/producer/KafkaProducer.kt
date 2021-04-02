package com.quickstart.producer

import com.quickstart.person.dto.PersonMessageDTO
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.messaging.annotation.MessageBody
import java.util.UUID

@KafkaClient
interface KafkaProducer {
    @Topic(PERSON_PRODUCER_TOPIC)
    suspend fun sendMessage(@KafkaKey key: UUID, @MessageBody person: PersonMessageDTO)

    companion object {
        private const val PERSON_PRODUCER_TOPIC = "person-event-create"
    }
}