package com.quickstart.person.usecase

import com.quickstart.person.model.Person
import com.quickstart.person.ports.input.PersonInputPort
import com.quickstart.person.model.PersonEventType.CREATE
import com.quickstart.person.ports.output.PersonDataAccessPort
import com.quickstart.person.ports.output.PersonEventProducerPort
import javax.inject.Singleton
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory

@Singleton
class PersonUseCase(
    private val personDataAccessPort: PersonDataAccessPort,
    private val personEventProducerPort: PersonEventProducerPort
) : PersonInputPort {
    override suspend fun save(person: Person) = coroutineScope {
        logger.info("Saving Person: $person")

        personDataAccessPort.save(person).also { personSaved ->
            logger.info("Person Saved: $personSaved")
            personEventProducerPort.notifyMessage(person=personSaved, personEventType = CREATE)
        }
    }

    override suspend fun findAll(): List<Person> = coroutineScope {
        personDataAccessPort.findAll()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}