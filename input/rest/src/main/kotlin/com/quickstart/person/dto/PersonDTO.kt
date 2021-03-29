package com.quickstart.person.dto

import com.quickstart.person.model.Person

data class PersonDTO (
    val name: String,
    val email: String,
    val cpf: String
) {
    fun toModel() = Person(
        name = name,
        email = email,
        cpf = cpf
    )
}

fun Person.toDTO() = PersonDTO(
    name = name,
    email = email,
    cpf = cpf
)
