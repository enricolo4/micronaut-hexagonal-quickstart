package com.quickstart.person.exceptions

class PersonEventProducerException(message: String): RuntimeException(message)

class PersonAlreadyExistsException(message: String): RuntimeException(message)