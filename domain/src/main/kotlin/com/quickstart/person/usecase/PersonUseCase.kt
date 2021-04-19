package com.quickstart.person.usecase

import com.quickstart.person.exceptions.PersonAlreadyExistsException
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
    override suspend fun save(person: Person): Person {
        logger.info("Saving Person: $person")

        if(personDataAccessPort.existsByCpf(person.cpf))
            throw PersonAlreadyExistsException("Person with cpf: ${person.cpf} already exists")

        return personDataAccessPort.save(person).also { personSaved ->
            logger.info("Person Saved: $personSaved")
            personEventProducerPort.notifyMessage(person = personSaved, personEventType = CREATE)
        }
    }

    override suspend fun findAll(): List<Person> = coroutineScope {
        personDataAccessPort.findAll()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}