package com.musinsa.assignment.category.domain.exceptions

import com.musinsa.assignment.category.support.exceptions.client.AbstractConflictErrorException

class AlreadyAssignedRootCategoryException(
    reason: String = "Already Assigned Root Category",
) : AbstractConflictErrorException(reason = reason)
