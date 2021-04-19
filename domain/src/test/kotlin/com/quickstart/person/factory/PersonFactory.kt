package com.quickstart.person.factory

import com.quickstart.person.model.Person
import java.util.UUID

fun personFactory(
    id: UUID? = UUID.randomUUID(),
    name: String = "Name Test",
    email: String = "email@test.com.br",
    cpf: String = "71043112030"
) = Person(id = id, name = name, email = email, cpf = cpf)