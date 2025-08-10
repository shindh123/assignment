package com.musinsa.assignment.category.adapter.output.persistence

import com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper.buildReadModel
import com.musinsa.assignment.category.adapter.output.persistence.jpa.repository.DisplayCategoryContextMappingEntityRepository
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.application.port.output.DisplayCategoryNodeOutputPort
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayId
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryNode
import org.springframework.stereotype.Repository

@Repository
class DisplayCategoryNodePersistenceAdapter(
    private val displayCategoryContextMappingEntityRepository: DisplayCategoryContextMappingEntityRepository,
) : DisplayCategoryNodeOutputPort {
    override fun findByDisplayIdAndCategoryId(
        displayId: DisplayId,
        rootId: CategoryId,
    ): DisplayCategoryNode =
        displayCategoryContextMappingEntityRepository
            .findSubTree(
                rootId = rootId.value,
                displayId = displayId.value,
            ).let { it.buildReadModel() }
}
