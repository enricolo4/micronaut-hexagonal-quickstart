package com.quickstart.person.exceptions

class PersonEventProducerException(message: String): RuntimeException(message)

class PersonAlreadyExistsException(message: String): RuntimeException(message)
class PersonNotExistsException(message: String): RuntimeException(message)