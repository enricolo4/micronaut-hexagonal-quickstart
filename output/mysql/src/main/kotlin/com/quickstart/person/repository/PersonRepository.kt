package com.quickstart.person.repository

import com.quickstart.person.dbo.PersonDBO
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.r2dbc.repository.ReactorCrudRepository
import reactor.core.publisher.Mono

@R2dbcRepository(dialect = Dialect.MYSQL)
internal interface PersonRepository: ReactorCrudRepository<PersonDBO, String> {
    suspend fun findByCpf(cpf: String): Mono<PersonDBO>
    fun existsByCpf(cpf: String): Boolean
}