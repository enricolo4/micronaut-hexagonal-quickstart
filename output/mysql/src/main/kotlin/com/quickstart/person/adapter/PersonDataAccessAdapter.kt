package com.quickstart.person.adapter

import com.quickstart.person.dbo.toDBO
import com.quickstart.person.model.Person
import com.quickstart.person.ports.output.PersonDataAccessPort
import com.quickstart.person.repository.PersonRepository
import javax.inject.Singleton
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle

@Singleton
internal class PersonDataAccessAdapter(
    private val personRepository: PersonRepository
) : PersonDataAccessPort {
    override suspend fun save(person: Person) = coroutineScope {
        personRepository.save(person.toDBO())
            .awaitSingle()
            .toModel()
    }

    override suspend fun findAll(): List<Person> = coroutineScope {
        personRepository
            .findAll()
            .asFlow()
            .toList()
            .map { personDBO ->
                personDBO.toModel()
            }
    }
}