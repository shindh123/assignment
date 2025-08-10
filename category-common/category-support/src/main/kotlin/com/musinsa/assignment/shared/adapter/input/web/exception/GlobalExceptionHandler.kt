package com.musinsa.assignment.shared.adapter.input.web.exception

import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler(
    private val resolver: ErrorResolver,
) : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Throwable::class)
    fun handleUnknownException(throwable: Throwable): ProblemDetail = resolver.resolve(throwable)
}
