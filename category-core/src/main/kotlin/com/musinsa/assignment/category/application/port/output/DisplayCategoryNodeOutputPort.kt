package com.musinsa.assignment.category.application.port.output

import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayId
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryNode

interface DisplayCategoryNodeOutputPort {
    fun findByDisplayIdAndCategoryId(
        displayId: DisplayId,
        rootId: CategoryId,
    ): DisplayCategoryNode
}
