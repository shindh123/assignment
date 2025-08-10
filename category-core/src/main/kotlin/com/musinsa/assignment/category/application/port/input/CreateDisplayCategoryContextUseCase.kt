package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.DisplayCategoryContext

interface CreateDisplayCategoryContextUseCase {
    fun create(command: CreateDisplayCategoryContextCommand): DisplayCategoryContext

    data class CreateDisplayCategoryContextCommand(
        val categoryId: Long,
        val displayId: Long,
        val parentId: Long? = null,
        val order: Int,
    )
}
