package com.musinsa.assignment.category.domain.exceptions

import com.musinsa.assignment.category.support.exceptions.client.AbstractNotFoundErrorException

class DisplayCategoryNodeNotFoundException(
    reason: String = "DisplayCategoryNode Not Found",
) : AbstractNotFoundErrorException(reason = reason)
