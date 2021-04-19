package com.quickstart.person.controller

import com.quickstart.person.dto.PersonDTO
import com.quickstart.person.dto.toDTO
import com.quickstart.person.ports.input.PersonInputPort
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/person")
class PersonController(
    private val personInputPort: PersonInputPort
) {
    @Post(produces = [APPLICATION_JSON])
    suspend fun save(@Body personDTO: PersonDTO) = personInputPort.save(personDTO.toModel()).toDTO()

    @Get(produces = [APPLICATION_JSON])
    suspend fun findAll() = personInputPort.findAll()
        .map { person ->
            person.toDTO()
        }
}