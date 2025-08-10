package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.Category
import com.musinsa.assignment.category.domain.CategoryId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetCategoryUseCase {
    fun getCategoryById(id: CategoryId): Category

    fun getAllCategories(pageable: Pageable): Page<Category>
}
