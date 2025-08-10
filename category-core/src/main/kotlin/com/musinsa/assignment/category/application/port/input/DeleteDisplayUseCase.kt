package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.DisplayId

interface DeleteDisplayUseCase {
    fun delete(command: DeleteDisplayCommand)

    data class DeleteDisplayCommand(
        val id: DisplayId,
    )
}
