package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.Category
import com.musinsa.assignment.category.domain.CategoryId

interface RenameCategoryUseCase {
    fun rename(command: RenameCategoryCommand): Category

    data class RenameCategoryCommand(
        val id: CategoryId,
        val name: String,
    )
}
