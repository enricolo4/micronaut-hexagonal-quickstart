package com.quickstart.person.ports.input

import com.quickstart.person.model.Person

interface PersonInputPort {
    suspend fun save(person: Person): Person
}