package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryContext
import com.musinsa.assignment.category.domain.DisplayId

interface AssignCategoryToDisplayUseCase {
    fun assignCategoryToDisplay(command: AssignCategoryToDisplayCommand): DisplayCategoryContext

    data class AssignCategoryToDisplayCommand(
        val displayId: DisplayId,
        val categoryId: CategoryId,
        val parentId: CategoryId? = null,
        val order: Int,
    )
}
