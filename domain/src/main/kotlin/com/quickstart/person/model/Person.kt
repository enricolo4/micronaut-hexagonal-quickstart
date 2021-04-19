package com.quickstart.person.model

import java.util.UUID

data class Person(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val cpf: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int = id.hashCode()

    fun toUpdate(person: Person) = copy(
        name = person.name,
        email = person.email,
        cpf = person.cpf
    )
}

enum class PersonEventType {
    CREATE,
    UPDATE
}