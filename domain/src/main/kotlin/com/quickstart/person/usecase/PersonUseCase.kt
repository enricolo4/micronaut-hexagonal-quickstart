package com.quickstart.person.usecase

import com.quickstart.person.model.Person
import com.quickstart.person.ports.input.PersonInputPort
import com.quickstart.person.ports.output.PersonDataAccessPort
import javax.inject.Singleton
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory

@Singleton
class PersonUseCase(
    private val personDataAccessPort: PersonDataAccessPort
): PersonInputPort {
    override suspend fun save(person: Person) = coroutineScope {
        logger.info("Saving Person: $person")

        personDataAccessPort.save(person)
            .also { personSaved -> logger.info("Person Saved: $personSaved") }
    }

    override suspend fun findAll(): List<Person> = coroutineScope {
        personDataAccessPort.findAll()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}