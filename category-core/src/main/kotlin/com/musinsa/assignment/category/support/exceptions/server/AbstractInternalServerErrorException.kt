package com.musinsa.assignment.category.support.exceptions.server

import com.musinsa.assignment.category.support.exceptions.AbstractCategoryException

abstract class AbstractInternalServerErrorException(
    reason: String? = null,
    cause: Throwable? = null,
) : AbstractCategoryException(
        status = INTERNAL_SERVER_ERROR_STATUS,
        reason = reason,
        cause = cause,
    ) {
    companion object {
        const val INTERNAL_SERVER_ERROR_STATUS: Int = 500
    }
}
