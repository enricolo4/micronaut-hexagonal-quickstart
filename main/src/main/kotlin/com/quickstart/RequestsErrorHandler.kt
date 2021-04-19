package com.quickstart

import com.quickstart.person.exceptions.PersonAlreadyExistsException
import com.quickstart.person.exceptions.PersonNotExistsException
import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
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
            PersonNotExistsException::class,
            RuntimeException::class,
            ExceptionHandler::class
        ]
    )
)
class PersonAlreadyExistsExceptionHandler(
    private val errorResponseProcessor: ErrorResponseProcessor<Any>
) : ExceptionHandler<RuntimeException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: RuntimeException): HttpResponse<out Any> = when (exception) {
        is PersonAlreadyExistsException -> handlePersonAlreadyExistsException(request, exception)
        is PersonNotExistsException -> handlePersonNotExistsException(request, exception)
        else -> handleServerErrorException(request, exception)
    }

    private fun handlePersonAlreadyExistsException(
        request: HttpRequest<*>,
        exception: PersonAlreadyExistsException
    ): HttpResponse<*> {
        return errorResponseProcessor.processResponse(
            ErrorContext.builder(request)
                .cause(exception)
                .errorMessage(exception.message!!)
                .build(), HttpResponse.unprocessableEntity<Any>()
        )
    }

    private fun handlePersonNotExistsException(
        request: HttpRequest<*>,
        exception: PersonNotExistsException
    ): HttpResponse<*> {
        return errorResponseProcessor.processResponse(
            ErrorContext.builder(request)
                .cause(exception)
                .errorMessage(exception.message!!)
                .build(), HttpResponse.notFound<Any>()
        )
    }

    private fun handleServerErrorException(
        request: HttpRequest<*>,
        exception: RuntimeException
    ): HttpResponse<*> {
        return errorResponseProcessor.processResponse(
            ErrorContext.builder(request)
                .cause(exception)
                .build(), HttpResponse.serverError<Any>()
        )
    }
}