package com.quickstart.person.usecase

import com.quickstart.person.model.Person
import com.quickstart.person.ports.input.PersonInputPort
import com.quickstart.person.ports.output.PersonDataAccessPort
import javax.inject.Singleton

@Singleton
class PersonUseCase(
    private val personDataAccessPort: PersonDataAccessPort
): PersonInputPort {
    override suspend fun save(person: Person): Person {
        return Person("Brunão Lindo", "brunao_lindao@lindodemais.com", "11111111111")
    }
}