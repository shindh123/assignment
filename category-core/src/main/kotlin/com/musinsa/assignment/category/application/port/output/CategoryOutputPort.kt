package com.musinsa.assignment.category.application.port.output

import com.musinsa.assignment.category.domain.Category
import com.musinsa.assignment.category.domain.CategoryId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CategoryOutputPort {
    fun save(category: Category): Category

    fun delete(id: CategoryId)

    fun findById(id: CategoryId): Category?

    fun findAll(): List<Category>

    fun findAll(pageable: Pageable): Page<Category>

    fun findByName(name: String): Category?
}
