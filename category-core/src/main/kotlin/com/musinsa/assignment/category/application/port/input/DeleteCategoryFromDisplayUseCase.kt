package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayId

interface DeleteCategoryFromDisplayUseCase {
    fun delete(command: DeleteCategoryFromDisplayCommand)

    data class DeleteCategoryFromDisplayCommand(
        val displayId: DisplayId,
        val categoryId: CategoryId,
    )
}
