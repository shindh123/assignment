package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryContext
import com.musinsa.assignment.category.domain.DisplayCategoryContextId
import com.musinsa.assignment.category.domain.DisplayId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetCategoryDisplayMappingUseCase {
    fun getMappingById(id: DisplayCategoryContextId): DisplayCategoryContext

    fun getMapping(query: GetCategoryDisplayMappingQuery): DisplayCategoryContext

    fun getMappings(query: GetCategoryDisplayMappingsQuery): Page<DisplayCategoryContext>

    data class GetCategoryDisplayMappingQuery(
        val displayId: DisplayId,
        val categoryId: CategoryId,
    )

    data class GetCategoryDisplayMappingsQuery(
        val displayId: DisplayId,
        val pageable: Pageable,
    )
}
