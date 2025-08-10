package com.musinsa.assignment.shared.adapter.input.web.exception

import org.springframework.http.ProblemDetail

interface ErrorResolver {
    fun resolve(throwable: Throwable): ProblemDetail
}
