package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryContext
import com.musinsa.assignment.category.domain.DisplayCategoryContextId
import com.musinsa.assignment.category.domain.DisplayId

interface UpdateDisplayCategoryContextUseCase {
    fun update(command: UpdateDisplayCategoryContextCommand): DisplayCategoryContext

    fun updateAll(commands: List<UpdateDisplayCategoryContextCommand>): List<DisplayCategoryContext>

    data class UpdateDisplayCategoryContextCommand(
        val id: DisplayCategoryContextId,
        val categoryId: CategoryId,
        val displayId: DisplayId,
        val parentId: CategoryId? = null,
        val order: Int,
    )
}
