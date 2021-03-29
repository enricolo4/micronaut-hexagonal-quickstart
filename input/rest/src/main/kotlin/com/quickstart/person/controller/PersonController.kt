package com.quickstart.person.controller

import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/person")
class PersonController {
    @Get(produces = [APPLICATION_JSON])
    fun save(): Map<String, String> {
        return mapOf("teste" to "123")
    }
}