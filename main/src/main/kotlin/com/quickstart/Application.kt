package com.quickstart

import com.quickstart.person.controller.PersonController
import com.quickstart.person.model.Person
import io.micronaut.context.BeanContext
import io.micronaut.runtime.mapError
import io.micronaut.runtime.startApplication

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            startApplication<Application>(*args) {
                packages("com.quickstart")


                BeanContext.run().getBean(PersonController::class.java)

                mapError<RuntimeException> { 500 }
            }
        }
    }
}

