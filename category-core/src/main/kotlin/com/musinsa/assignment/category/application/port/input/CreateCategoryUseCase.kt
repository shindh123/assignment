package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.Category

interface CreateCategoryUseCase {
    fun create(command: CreateCategoryCommand): Category

    data class CreateCategoryCommand(
        val name: String,
    )
}
