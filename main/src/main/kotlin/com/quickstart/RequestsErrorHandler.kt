package com.quickstart

import com.quickstart.person.exceptions.PersonAlreadyExistsException
import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.serverError
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor
import javax.inject.Singleton


@Produces
@Singleton
@Requirements(
    Requires(
        classes = [
            PersonAlreadyExistsException::class,
            ExceptionHandler::class
        ]
    )
)
class PersonAlreadyExistsExceptionHandler(
    private val errorResponseProcessor: ErrorResponseProcessor<Any>
) : ExceptionHandler<RuntimeException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: RuntimeException): HttpResponse<out Any> = when (exception) {
        is PersonAlreadyExistsException -> handlePersonAlreadyExistsException(request, exception)
        else -> serverError()
    }

    private fun handlePersonAlreadyExistsException(
        request: HttpRequest<*>,
        exception: PersonAlreadyExistsException
    ): HttpResponse<*> {
        return errorResponseProcessor.processResponse(
            ErrorContext.builder(request)
                .cause(exception)
                .errorMessage("Person Already Exists")
                .build(), HttpResponse.unprocessableEntity<Any>()
        )
    }
}