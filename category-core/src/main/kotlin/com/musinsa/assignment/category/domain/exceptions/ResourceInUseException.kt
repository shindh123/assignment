package com.musinsa.assignment.category.domain.exceptions

import com.musinsa.assignment.category.support.exceptions.client.AbstractConflictErrorException

class ResourceInUseException(
    reason: String = "Resource is currently in use",
) : AbstractConflictErrorException(reason = reason)
