package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.GetCategoryDisplayMappingUseCase
import com.musinsa.assignment.category.application.port.input.GetCategoryDisplayMappingUseCase.GetCategoryDisplayMappingQuery
import com.musinsa.assignment.category.application.port.input.GetCategoryDisplayMappingUseCase.GetCategoryDisplayMappingsQuery
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.domain.DisplayCategoryContext
import com.musinsa.assignment.category.domain.DisplayCategoryContextId
import com.musinsa.assignment.category.domain.exceptions.DisplayCategoryContextNotFoundException
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class DisplayCategoryContextQueryService(
    private val displayCategoryContextOutputPort: DisplayCategoryContextOutputPort,
) : GetCategoryDisplayMappingUseCase {
    override fun getMappingById(id: DisplayCategoryContextId): DisplayCategoryContext =
        displayCategoryContextOutputPort.findById(id)
            ?: throw DisplayCategoryContextNotFoundException("DisplayCategoryContext with id ${id.value} not found")

    override fun getMapping(query: GetCategoryDisplayMappingQuery): DisplayCategoryContext =
        displayCategoryContextOutputPort.findByDisplayIdAndCategoryId(
            displayId = query.displayId,
            categoryId = query.categoryId,
        )
            ?: throw DisplayCategoryContextNotFoundException(
                "DisplayCategoryContext with displayId ${query.displayId.value} and categoryId ${query.categoryId.value} not found",
            )

    override fun getMappings(query: GetCategoryDisplayMappingsQuery): Page<DisplayCategoryContext> =
        displayCategoryContextOutputPort.findAllByDisplayId(displayId = query.displayId, pageable = query.pageable)
}
