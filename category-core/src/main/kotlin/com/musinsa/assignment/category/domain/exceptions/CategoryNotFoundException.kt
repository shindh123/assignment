package com.musinsa.assignment.category.domain.exceptions

import com.musinsa.assignment.category.support.exceptions.client.AbstractNotFoundErrorException

class CategoryNotFoundException(
    reason: String = "Category Not Found",
) : AbstractNotFoundErrorException(reason = reason)
