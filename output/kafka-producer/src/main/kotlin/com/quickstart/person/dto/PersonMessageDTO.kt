package com.quickstart.person.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.quickstart.person.model.Person
import com.quickstart.person.model.PersonEventType
import java.util.UUID

data class PersonMessageDTO(
    @JsonProperty
    val id: UUID,
    @JsonProperty
    val name: String,
    @JsonProperty
    val cpf: String,
    @JsonProperty
    val email: String,
    @JsonProperty
    val type: PersonEventTypeDTO
)

enum class PersonEventTypeDTO {
    CREATE,
    UPDATE
}

fun PersonEventType.toDTO() = PersonEventTypeDTO.valueOf(name)

fun Person.toEventCreate(personEventType: PersonEventType) = PersonMessageDTO(
    id = id!!,
    name = name,
    cpf = cpf,
    email = email,
    type = personEventType.toDTO()
)