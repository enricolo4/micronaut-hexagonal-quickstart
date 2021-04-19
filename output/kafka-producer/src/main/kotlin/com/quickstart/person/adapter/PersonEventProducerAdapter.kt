package com.quickstart.person.adapter

import com.quickstart.person.dto.PersonMessageDTO
import com.quickstart.person.dto.toEventCreate
import com.quickstart.person.exceptions.PersonEventProducerException
import com.quickstart.person.model.Person
import com.quickstart.person.model.PersonEventType
import com.quickstart.person.ports.output.PersonEventProducerPort
import com.quickstart.producer.KafkaProducer
import javax.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class PersonEventProducerAdapter(
    private val kafkaProducer: KafkaProducer
) : PersonEventProducerPort {
    override suspend fun notifyMessage(person: Person, personEventType: PersonEventType) {
        runCatching {
            val personMessageDTO = person.toEventCreate(personEventType)
            notifyMessage(personMessageDTO)
        }
            .onSuccess { logger.info("Person event sent for: $person") }
            .onFailure { throw PersonEventProducerException("Could not send person event for: $person") }
    }

    private suspend fun notifyMessage(personMessageDTO: PersonMessageDTO) {
        logger.info("Sending person event: $personMessageDTO")
        kafkaProducer.sendMessage(personMessageDTO.id, personMessageDTO)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}