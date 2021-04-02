package com.quickstart.person.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class PersonMessageConsumerDTO (
    @JsonProperty
    val id: UUID,
    @JsonProperty
    val name: String,
    @JsonProperty
    val cpf: String,
    @JsonProperty
    val email: String,
    @JsonProperty
    val type: PersonEventConsumerTypeDTO
)

enum class PersonEventConsumerTypeDTO {
    CREATE,
    UPDATE
}