package com.musinsa.assignment.shared.adapter.input.web.exception

import com.musinsa.assignment.category.support.exceptions.AbstractCategoryException
import org.springframework.http.ProblemDetail
import org.springframework.stereotype.Component
import org.springframework.web.ErrorResponse

@Component
class DefaultErrorResolver : ErrorResolver {
    override fun resolve(throwable: Throwable): ProblemDetail {
        var problemDetail: ProblemDetail
        if (throwable is ErrorResponse) {
            problemDetail = throwable.body
        } else if (throwable is AbstractCategoryException) {
            problemDetail = ProblemDetail.forStatus(throwable.status)
            problemDetail.type = throwable.type()
            problemDetail.title = throwable.reason
        } else {
            problemDetail = ProblemDetail.forStatus(500)
            problemDetail.title = throwable.message
        }
        return problemDetail
    }
}
