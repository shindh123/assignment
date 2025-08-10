package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.CreateDisplayUseCase
import com.musinsa.assignment.category.application.port.input.CreateDisplayUseCase.CreateDisplayCommand
import com.musinsa.assignment.category.application.port.input.DeleteDisplayUseCase
import com.musinsa.assignment.category.application.port.input.UpdateDisplayUseCase
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.application.port.output.DisplayOutputPort
import com.musinsa.assignment.category.domain.Display
import com.musinsa.assignment.category.domain.exceptions.DisplayNotFoundException
import com.musinsa.assignment.category.domain.exceptions.ResourceInUseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DisplayCommandService(
    private val displayOutputPort: DisplayOutputPort,
    private val displayCategoryContextOutputPort: DisplayCategoryContextOutputPort,
) : CreateDisplayUseCase,
    UpdateDisplayUseCase,
    DeleteDisplayUseCase {
    @Transactional
    override fun create(command: CreateDisplayCommand): Display =
        displayOutputPort.findByName(command.name) ?: run {
            val display =
                Display(
                    name = command.name,
                    description = command.description,
                )
            displayOutputPort.save(display)
        }

    @Transactional
    override fun update(command: UpdateDisplayUseCase.UpdateDisplayCommand): Display {
        val existingDisplay =
            displayOutputPort.findById(command.id)
                ?: throw DisplayNotFoundException("Display with id ${command.id} does not exist")

        val updatedDisplay =
            existingDisplay.copy(
                name = command.name,
                description = command.description,
            )
        return displayOutputPort.save(updatedDisplay)
    }

    @Transactional
    override fun delete(command: DeleteDisplayUseCase.DeleteDisplayCommand) {
        val exists = displayCategoryContextOutputPort.findByDisplayId(displayId = command.id)
        if (exists.isNotEmpty()) {
            throw ResourceInUseException("Display with id ${command.id} is in use by category mappings")
        }
        displayOutputPort.delete(command.id)
    }
}
