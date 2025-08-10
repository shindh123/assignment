package com.musinsa.assignment.category.application.port.output

import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryContext
import com.musinsa.assignment.category.domain.DisplayCategoryContextId
import com.musinsa.assignment.category.domain.DisplayId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface DisplayCategoryContextOutputPort {
    fun save(context: DisplayCategoryContext): DisplayCategoryContext

    fun deleteById(id: DisplayCategoryContextId)

    fun deleteByDisplayIdAndCategoryId(
        displayId: DisplayId,
        categoryId: CategoryId,
    )

    fun findById(id: DisplayCategoryContextId): DisplayCategoryContext?

    fun findByDisplayIdAndCategoryId(
        displayId: DisplayId,
        categoryId: CategoryId,
    ): DisplayCategoryContext?

    fun findByDisplayId(displayId: DisplayId): List<DisplayCategoryContext>

    fun findByCategoryId(categoryId: CategoryId): List<DisplayCategoryContext>

    fun findAll(pageable: Pageable): Page<DisplayCategoryContext>

    fun findAllByDisplayId(
        displayId: DisplayId,
        pageable: Pageable,
    ): Page<DisplayCategoryContext>

    fun findRootCategoryByDisplayId(displayId: DisplayId): DisplayCategoryContext?
}
