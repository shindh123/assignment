package com.musinsa.assignment.category.domain.exceptions

import com.musinsa.assignment.category.support.exceptions.client.AbstractNotFoundErrorException

class DisplayNotFoundException(
    reason: String = "Display Not Found",
) : AbstractNotFoundErrorException(reason = reason)
