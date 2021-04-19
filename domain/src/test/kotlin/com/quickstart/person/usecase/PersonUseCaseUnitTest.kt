package com.quickstart.person.usecase

import com.quickstart.person.exceptions.PersonAlreadyExistsException
import com.quickstart.person.factory.personFactory
import com.quickstart.person.model.PersonEventType
import com.quickstart.person.ports.output.PersonDataAccessPort
import com.quickstart.person.ports.output.PersonEventProducerPort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.coEvery
import io.mockk.mockk
import java.util.UUID.randomUUID

@MicronautTest
class PersonUseCaseUnitTest : BehaviorSpec({
    val personDataAccessPort = mockk<PersonDataAccessPort>()
    val personEventProducerPort = mockk<PersonEventProducerPort>()
    val personUseCase = PersonUseCase(personDataAccessPort, personEventProducerPort)

    Given("a person with cpf in database") {
        When("try save as new person") {
            val person = personFactory(id = null)

            coEvery { personDataAccessPort.existsByCpf(person.cpf) } answers { true }

            Then("should throw PersonAlreadyExistsException") {
                shouldThrow<PersonAlreadyExistsException> { personUseCase.save(person) }
            }
        }
    }

    Given("a person with cpf not in database") {
        When("try save as new person") {
            val person = personFactory()
            val savedPerson = person.copy(id = randomUUID())

            coEvery { personDataAccessPort.existsByCpf(person.cpf) } answers { false }
            coEvery { personDataAccessPort.save(person) } answers { savedPerson }

            Then("should save new Person") {
                val response = personDataAccessPort.save(person)

                savedPerson shouldBe response
            }
        }
    }
})