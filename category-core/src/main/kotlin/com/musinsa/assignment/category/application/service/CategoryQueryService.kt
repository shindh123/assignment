package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.GetCategoryUseCase
import com.musinsa.assignment.category.application.port.output.CategoryOutputPort
import com.musinsa.assignment.category.domain.Category
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.exceptions.CategoryNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CategoryQueryService(
    private val categoryOutputPort: CategoryOutputPort,
) : GetCategoryUseCase {
    override fun getCategoryById(id: CategoryId): Category =
        categoryOutputPort.findById(id)
            ?: throw CategoryNotFoundException("Category with id ${id.value} not found")

    override fun getAllCategories(pageable: Pageable): Page<Category> = categoryOutputPort.findAll(pageable)
}
