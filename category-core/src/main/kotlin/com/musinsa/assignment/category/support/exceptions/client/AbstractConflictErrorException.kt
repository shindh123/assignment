package com.musinsa.assignment.category.support.exceptions.client

import com.musinsa.assignment.category.support.exceptions.AbstractCategoryException

abstract class AbstractConflictErrorException(
    reason: String? = null,
    cause: Throwable? = null,
) : AbstractCategoryException(
        status = CONFLICT_STATUS,
        reason = reason,
        cause = cause,
    ) {
    companion object {
        const val CONFLICT_STATUS: Int = 409
    }
}
