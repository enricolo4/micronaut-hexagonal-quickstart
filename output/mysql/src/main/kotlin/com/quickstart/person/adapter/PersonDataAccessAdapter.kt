package com.quickstart.person.adapter

import com.quickstart.person.dbo.toDBO
import com.quickstart.person.model.Person
import com.quickstart.person.ports.output.PersonDataAccessPort
import com.quickstart.person.repository.PersonRepository
import javax.inject.Singleton
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull

@Singleton
internal class PersonDataAccessAdapter(
    private val personRepository: PersonRepository
) : PersonDataAccessPort {
    override suspend fun save(person: Person) = personRepository.save(person.toDBO())
        .awaitSingle()
        .toModel()

    override suspend fun findByCpf(cpf: String) = personRepository
        .findByCpf(cpf)
        .awaitSingleOrNull()
        ?.toModel()

    override suspend fun existsByCpf(cpf: String): Boolean = personRepository
        .existsByCpf(cpf)

    override suspend fun update(person: Person) = personRepository
        .update(person.toDBO())
        .awaitSingleOrNull()
        .toModel()

    override suspend fun findAll() = personRepository
        .findAll()
        .asFlow()
        .toList()
        .map { personDBO ->
            personDBO.toModel()
        }
}