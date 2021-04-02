package com.quickstart.person.model

import java.util.UUID

data class Person(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val cpf: String
)

enum class PersonEventType {
    CREATE,
    UPDATE
}