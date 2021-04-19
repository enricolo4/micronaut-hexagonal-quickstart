package com.quickstart.person.controller

import com.quickstart.person.dto.PersonDTO
import com.quickstart.person.dto.toDTO
import com.quickstart.person.ports.input.PersonInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

@Controller("/person")
class PersonController(
    private val personInputPort: PersonInputPort
) {
    @Post(produces = [APPLICATION_JSON])
    suspend fun save(@Body personDTO: PersonDTO) = personInputPort
        .save(personDTO.toModel())
        .toDTO()

    @Put("{cpf}", produces = [APPLICATION_JSON])
    suspend fun update(
        @Body personDTO: PersonDTO,
        @PathVariable cpf: String
    ) = personInputPort
        .update(cpf = cpf, person = personDTO.toModel())
        .toDTO()

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = personInputPort.findAll()
        .map { person ->
            person.toDTO()
        }
}