package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayId
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryGroup
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryNode

interface LoadDisplayCategoryGroupUseCase {
    fun loadDisplayCategoryGroupByDisplayId(query: DisplayCategoryGroupQuery): DisplayCategoryGroup

    data class DisplayCategoryGroupQuery(
        val displayId: DisplayId,
        val rootId: CategoryId? = null,
    )
}
