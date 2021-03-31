package com.quickstart.person.ports.output

import com.quickstart.person.model.Person

interface PersonDataAccessPort {
    suspend fun save(person: Person): Person
    suspend fun findAll(): List<Person>
}