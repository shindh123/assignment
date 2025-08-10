package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.Display
import com.musinsa.assignment.category.domain.DisplayId

interface UpdateDisplayUseCase {
    fun update(command: UpdateDisplayCommand): Display

    data class UpdateDisplayCommand(
        val id: DisplayId,
        val name: String,
        val description: String? = null,
    )
}
