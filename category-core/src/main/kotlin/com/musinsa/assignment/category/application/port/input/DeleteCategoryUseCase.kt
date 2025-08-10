package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.CategoryId

interface DeleteCategoryUseCase {
    fun delete(command: DeleteCategoryCommand)

    data class DeleteCategoryCommand(
        val id: CategoryId,
    )
}
