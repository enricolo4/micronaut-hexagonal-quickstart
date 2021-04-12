package com.quickstart.shared

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import java.time.Instant
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Auditable {
    @DateCreated
    lateinit var createdAt: Instant
    @DateUpdated
    var modifiedAt: Instant? = null
}