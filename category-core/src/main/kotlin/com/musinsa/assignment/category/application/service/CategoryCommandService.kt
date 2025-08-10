package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.CreateCategoryUseCase
import com.musinsa.assignment.category.application.port.input.CreateCategoryUseCase.CreateCategoryCommand
import com.musinsa.assignment.category.application.port.input.DeleteCategoryUseCase
import com.musinsa.assignment.category.application.port.input.DeleteCategoryUseCase.DeleteCategoryCommand
import com.musinsa.assignment.category.application.port.input.RenameCategoryUseCase
import com.musinsa.assignment.category.application.port.input.RenameCategoryUseCase.RenameCategoryCommand
import com.musinsa.assignment.category.application.port.output.CategoryOutputPort
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.domain.Category
import com.musinsa.assignment.category.domain.exceptions.CategoryNotFoundException
import com.musinsa.assignment.category.domain.exceptions.ResourceInUseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryCommandService(
    private val categoryOutputPort: CategoryOutputPort,
    private val displayCategoryContextOutputPort: DisplayCategoryContextOutputPort,
) : CreateCategoryUseCase,
    RenameCategoryUseCase,
    DeleteCategoryUseCase {
    @Transactional
    override fun create(command: CreateCategoryCommand): Category =
        categoryOutputPort.findByName(command.name) ?: run {
            val category = Category(name = command.name)
            categoryOutputPort.save(category)
        }

    @Transactional
    override fun rename(command: RenameCategoryCommand): Category {
        val existingCategory =
            categoryOutputPort.findById(command.id)
                ?: throw CategoryNotFoundException("Category with id ${command.id} does not exist")

        val updatedCategory = existingCategory.rename(command.name)
        return categoryOutputPort.save(updatedCategory)
    }

    @Transactional
    override fun delete(command: DeleteCategoryCommand) {
        val exists = displayCategoryContextOutputPort.findByCategoryId(categoryId = command.id)
        if (exists.isNotEmpty()) {
            throw ResourceInUseException("Category with id ${command.id} is in use by display mappings")
        }
        categoryOutputPort.delete(command.id)
    }
}
