package com.musinsa.assignment.category.support.exceptions.client

import com.musinsa.assignment.category.support.exceptions.AbstractCategoryException

abstract class AbstractNotFoundErrorException(
    reason: String? = null,
    cause: Throwable? = null,
) : AbstractCategoryException(
        status = NOT_FOUND_STATUS,
        reason = reason,
        cause = cause,
    ) {
    companion object {
        const val NOT_FOUND_STATUS: Int = 404
    }
}
