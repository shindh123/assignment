package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.AssignCategoryToDisplayUseCase
import com.musinsa.assignment.category.application.port.input.AssignCategoryToDisplayUseCase.AssignCategoryToDisplayCommand
import com.musinsa.assignment.category.application.port.input.DeleteCategoryFromDisplayUseCase
import com.musinsa.assignment.category.application.port.input.UpdateDisplayCategoryContextUseCase
import com.musinsa.assignment.category.application.port.input.UpdateDisplayCategoryContextUseCase.UpdateDisplayCategoryContextCommand
import com.musinsa.assignment.category.application.port.output.CategoryOutputPort
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryContext
import com.musinsa.assignment.category.domain.exceptions.AlreadyAssignedRootCategoryException
import com.musinsa.assignment.category.domain.exceptions.CategoryNotFoundException
import com.musinsa.assignment.category.domain.exceptions.DisplayCategoryContextNotFoundException
import com.musinsa.assignment.category.domain.extensions.ifPresentOrElse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DisplayCategoryContextCommandService(
    private val categoryOutputPort: CategoryOutputPort,
    private val displayCategoryContextOutputPort: DisplayCategoryContextOutputPort,
) : AssignCategoryToDisplayUseCase,
    UpdateDisplayCategoryContextUseCase,
    DeleteCategoryFromDisplayUseCase {
    @Transactional
    override fun assignCategoryToDisplay(command: AssignCategoryToDisplayCommand): DisplayCategoryContext {
        val mappingContext =
            DisplayCategoryContext(
                categoryId = command.categoryId,
                displayId = command.displayId,
                parentId = command.parentId,
                order = command.order,
            )
        // parentId가 null이 아닌 경우, 해당 카테고리가 존재하는지 확인
        // parentId가 null인 경우, 최상위 카테고리로 간주, 현재 display에 최상위 카테고리가 없는지 확인
        command.parentId.ifPresentOrElse(
            onPresent = ::existsParentCategory,
            onAbsent = { checkAlreadyAssignedRootCategory(command) },
        )
        return displayCategoryContextOutputPort.save(mappingContext)
    }

    private fun checkAlreadyAssignedRootCategory(command: AssignCategoryToDisplayCommand) {
        val existingRootContext =
            displayCategoryContextOutputPort.findRootCategoryByDisplayId(command.displayId)
        if (existingRootContext != null) {
            throw AlreadyAssignedRootCategoryException()
        }
    }

    private fun existsParentCategory(parentId: CategoryId) {
        categoryOutputPort.findById(parentId)
            ?: throw CategoryNotFoundException("Parent category with id ${parentId.value} not found")
    }

    @Transactional
    override fun update(command: UpdateDisplayCategoryContextCommand): DisplayCategoryContext {
        val existingContext =
            displayCategoryContextOutputPort.findById(command.id)
                ?: throw DisplayCategoryContextNotFoundException("DisplayCategoryContext with id ${command.id} not found")

        val updatedContext =
            existingContext.copy(
                categoryId = command.categoryId,
                displayId = command.displayId,
                parentId = command.parentId,
                order = command.order,
            )
        return displayCategoryContextOutputPort.save(updatedContext)
    }

    @Transactional
    override fun updateAll(commands: List<UpdateDisplayCategoryContextCommand>): List<DisplayCategoryContext> = commands.map { update(it) }

    @Transactional
    override fun delete(command: DeleteCategoryFromDisplayUseCase.DeleteCategoryFromDisplayCommand) {
        displayCategoryContextOutputPort.deleteByDisplayIdAndCategoryId(
            displayId = command.displayId,
            categoryId = command.categoryId,
        )
    }
}
