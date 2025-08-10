package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.LoadDisplayCategoryGroupUseCase
import com.musinsa.assignment.category.application.port.input.LoadDisplayCategoryGroupUseCase.DisplayCategoryGroupQuery
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.application.port.output.DisplayCategoryNodeOutputPort
import com.musinsa.assignment.category.application.port.output.DisplayOutputPort
import com.musinsa.assignment.category.domain.exceptions.DisplayCategoryContextNotFoundException
import com.musinsa.assignment.category.domain.exceptions.DisplayCategoryNodeNotFoundException
import com.musinsa.assignment.category.domain.exceptions.DisplayNotFoundException
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryGroup
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryNode
import com.musinsa.assignment.category.support.cache.CacheNames
import com.musinsa.assignment.category.support.cache.CategoryCache
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class DisplayCategoryGroupQueryService(
    private val displayOutputPort: DisplayOutputPort,
    private val displayCategoryContextOutputPort: DisplayCategoryContextOutputPort,
    private val displayCategoryNodeOutputPort: DisplayCategoryNodeOutputPort,
) : LoadDisplayCategoryGroupUseCase {
    // TODO: 일단 캐싱으로 처리하고 나중에 Read Model 생성해서 조회하는 것으로 변경
    @Cacheable(key = "#query.displayId.value + ':' + #query.rootId.value", cacheNames = [CacheNames.CATEGORY_TREE_NAME])
    override fun loadDisplayCategoryGroupByDisplayId(query: DisplayCategoryGroupQuery): DisplayCategoryGroup {
        val display =
            displayOutputPort.findById(query.displayId)
                ?: throw DisplayNotFoundException("Display with id ${query.displayId} not found")
        val rootId =
            query.rootId ?: displayCategoryContextOutputPort.findRootCategoryByDisplayId(query.displayId)?.categoryId
                ?: throw DisplayCategoryContextNotFoundException(
                    "No root category found for display with id ${query.displayId}",
                )
        val rootNode =
            displayCategoryNodeOutputPort.findByDisplayIdAndCategoryId(
                displayId = query.displayId,
                rootId = rootId,
            ) ?: throw DisplayCategoryNodeNotFoundException(
                "DisplayCategoryNode with displayId ${query.displayId} and categoryId ${query.rootId} not found",
            )
        return DisplayCategoryGroup(
            display = display,
            categoryRoot = rootNode,
        )
    }
}
