package com.quickstart.person.ports.output

import com.quickstart.person.model.Person
import com.quickstart.person.model.PersonEventType

interface PersonEventProducerPort {
    suspend fun notifyMessage(person: Person, personEventType: PersonEventType)
}