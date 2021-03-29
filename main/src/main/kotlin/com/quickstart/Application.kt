package com.quickstart

import io.micronaut.runtime.mapError
import io.micronaut.runtime.startApplication

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            startApplication<Application>(*args) {
                packages("com.quickstart")
                mapError<RuntimeException> { 500 }
            }
        }
    }
}

