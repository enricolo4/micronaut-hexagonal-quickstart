package com.quickstart.person.usecase

import com.quickstart.person.model.Person
import com.quickstart.person.ports.input.PersonInputPort
import javax.inject.Singleton

@Singleton
class PersonUseCase: PersonInputPort {

    override suspend fun save(person: Person): Person {
        return person
    }
}