package com.quickstart.person.dbo
import com.quickstart.person.model.Person
import com.quickstart.shared.Auditable
import io.micronaut.data.annotation.MappedEntity
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.Id

@MappedEntity("person")
internal data class PersonDBO(
    @Id
    val id: String,
    val name: String,
    val email: String,
    val cpf: String
): Auditable() {
    fun toModel() = Person(
        id = UUID.fromString(id),
        name = name,
        email = email,
        cpf = cpf
    )
}

internal fun Person.toDBO() = PersonDBO(
    id = id?.toString() ?: randomUUID().toString(),
    name = name,
    email = email,
    cpf = cpf
)