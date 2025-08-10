package com.musinsa.assignment.category.support.exceptions.client

import com.musinsa.assignment.category.support.exceptions.AbstractCategoryException

abstract class AbstractBadRequestErrorException(
    reason: String? = null,
    cause: Throwable? = null,
) : AbstractCategoryException(
        status = BAD_REQUEST_STATUS,
        reason = reason,
        cause = cause,
    ) {
    companion object {
        const val BAD_REQUEST_STATUS: Int = 400
    }
}
