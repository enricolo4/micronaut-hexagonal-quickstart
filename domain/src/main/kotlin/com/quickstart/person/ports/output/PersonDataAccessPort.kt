package com.quickstart.person.ports.output

import com.quickstart.person.model.Person

interface PersonDataAccessPort {
    suspend fun save(person: Person): Person
    suspend fun update(person: Person): Person
    suspend fun findAll(): List<Person>
    suspend fun findByCpf(cpf: String): Person?
    suspend fun existsByCpf(cpf: String): Boolean
}