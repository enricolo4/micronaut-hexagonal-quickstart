package com.quickstart.person.usecase

import com.quickstart.person.exceptions.PersonAlreadyExistsException
import com.quickstart.person.exceptions.PersonNotExistsException
import com.quickstart.person.factory.personFactory
import com.quickstart.person.model.PersonEventType.CREATE
import com.quickstart.person.model.PersonEventType.UPDATE
import com.quickstart.person.ports.output.PersonDataAccessPort
import com.quickstart.person.ports.output.PersonEventProducerPort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
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

            coEvery { personDataAccessPort.existsByCpf(person.cpf) } coAnswers { true }

            Then("should throw PersonAlreadyExistsException") {
                shouldThrow<PersonAlreadyExistsException> { personUseCase.save(person) }
            }
        }

        When("try to update person data") {
            val person = personFactory(id = null)
            val personFound = person.copy(id = randomUUID())
            val updatedPerson = personFound.copy(cpf = "71043112031")

            coEvery { personDataAccessPort.findByCpf(person.cpf) } coAnswers { personFound }
            coEvery { personDataAccessPort.update(updatedPerson) } coAnswers { updatedPerson }
            coEvery { personEventProducerPort.notifyMessage(updatedPerson, UPDATE) } coAnswers { }


            Then("should update Person data") {
                val response = personUseCase.update(person.cpf, person.copy(cpf = "71043112031"))

                updatedPerson shouldBe response
                updatedPerson shouldBe personFound

                updatedPerson.cpf shouldBe response.cpf
                updatedPerson.cpf shouldNotBe personFound.cpf
            }
        }
    }

    Given("a person without cpf in database") {
        When("try save as new person") {
            val person = personFactory(id = null, cpf = "71043112033")
            val savedPerson = person.copy(id = randomUUID())

            coEvery { personDataAccessPort.existsByCpf(person.cpf) } coAnswers { false }
            coEvery { personDataAccessPort.save(person) } coAnswers { savedPerson }
            coEvery { personEventProducerPort.notifyMessage(savedPerson, CREATE) } coAnswers { }

            Then("should save new Person") {
                val response = personUseCase.save(person)

                savedPerson shouldBe response
            }
        }

        When("try to update person data") {
            val person = personFactory()

            coEvery { personDataAccessPort.findByCpf(person.cpf) } coAnswers { null }

            Then("should throw PersonNotExistsException") {
                shouldThrow<PersonNotExistsException> { personUseCase.update(person.cpf, person) }
            }
        }
    }
})