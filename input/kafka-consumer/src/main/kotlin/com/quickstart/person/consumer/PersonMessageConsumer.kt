package com.quickstart.person.consumer

import com.quickstart.person.dto.PersonMessageConsumerDTO
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.messaging.annotation.MessageBody
import org.slf4j.LoggerFactory

@KafkaListener(
    offsetReset = OffsetReset.EARLIEST,
    groupId = "person-event-consumer"
)
class PersonMessageConsumer {
    @Topic(PERSON_PRODUCER_TOPIC)
    fun receive(@MessageBody personMessageConsumerDTO: PersonMessageConsumerDTO) {
        logger.info("Person Event Consumed: $personMessageConsumerDTO")
    }

    companion object {
        private const val PERSON_PRODUCER_TOPIC = "person-event-create"
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}