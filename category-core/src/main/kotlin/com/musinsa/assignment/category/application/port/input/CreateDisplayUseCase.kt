package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.Display

interface CreateDisplayUseCase {
    fun create(command: CreateDisplayCommand): Display

    data class CreateDisplayCommand(
        val name: String,
        val description: String? = null,
    )
}
