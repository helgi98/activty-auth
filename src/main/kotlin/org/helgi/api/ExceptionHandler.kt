package org.helgi.api;

import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor
import jakarta.inject.Singleton
import org.helgi.exception.ConflictException
import org.helgi.exception.NotFoundException
import org.helgi.exception.ValidationException


@Produces
@Singleton
@Requirements(
    Requires(classes = [ConflictException::class, ExceptionHandler::class])
)
class OutOfStockExceptionHandler(private val errorResponseProcessor: ErrorResponseProcessor<Any>) :
    ExceptionHandler<ConflictException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: ConflictException): HttpResponse<*> {
        return errorResponseProcessor.processResponse(
            ErrorContext.builder(request)
                .cause(exception)
                .errorMessage(exception.message ?: "Entities conflict")
                .build(), HttpResponse.status<Any>(HttpStatus.CONFLICT)
        )
    }
}
