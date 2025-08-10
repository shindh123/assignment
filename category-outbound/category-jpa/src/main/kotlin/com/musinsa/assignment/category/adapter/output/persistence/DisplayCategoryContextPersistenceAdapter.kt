package com.musinsa.assignment.category.adapter.output.persistence

import com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper.toDomain
import com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper.toEntity
import com.musinsa.assignment.category.adapter.output.persistence.jpa.repository.DisplayCategoryContextMappingEntityRepository
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryContext
import com.musinsa.assignment.category.domain.DisplayCategoryContextId
import com.musinsa.assignment.category.domain.DisplayId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class DisplayCategoryContextPersistenceAdapter(
    private val displayCategoryContextMappingEntityRepository: DisplayCategoryContextMappingEntityRepository,
) : DisplayCategoryContextOutputPort {
    override fun save(context: DisplayCategoryContext): DisplayCategoryContext {
        val saved = displayCategoryContextMappingEntityRepository.save(context.toEntity())
        return saved.toDomain()
    }

    override fun deleteById(id: DisplayCategoryContextId) {
        displayCategoryContextMappingEntityRepository.deleteById(id.value)
    }

    override fun deleteByDisplayIdAndCategoryId(
        displayId: DisplayId,
        categoryId: CategoryId,
    ) {
        displayCategoryContextMappingEntityRepository.deleteByDisplayIdAndCategoryId(
            displayId = displayId.value,
            categoryId = categoryId.value,
        )
    }

    override fun findById(id: DisplayCategoryContextId): DisplayCategoryContext? =
        displayCategoryContextMappingEntityRepository.findByIdOrNull(id.value)?.let {
            it.toDomain()
        }

    override fun findByDisplayIdAndCategoryId(
        displayId: DisplayId,
        categoryId: CategoryId,
    ): DisplayCategoryContext? =
        displayCategoryContextMappingEntityRepository
            .findByDisplayIdAndCategoryId(
                displayId = displayId.value,
                categoryId = categoryId.value,
            )?.let { it.toDomain() }

    override fun findByDisplayId(displayId: DisplayId): List<DisplayCategoryContext> =
        displayCategoryContextMappingEntityRepository
            .findByDisplayId(displayId.value)
            .map { it.toDomain() }

    override fun findByCategoryId(categoryId: CategoryId): List<DisplayCategoryContext> =
        displayCategoryContextMappingEntityRepository
            .findByCategoryId(categoryId.value)
            .map { it.toDomain() }

    override fun findAll(pageable: Pageable): Page<DisplayCategoryContext> =
        displayCategoryContextMappingEntityRepository.findAll(pageable).map {
            it.toDomain()
        }

    override fun findAllByDisplayId(
        displayId: DisplayId,
        pageable: Pageable,
    ): Page<DisplayCategoryContext> =
        displayCategoryContextMappingEntityRepository
            .findAllByDisplayId(displayId.value, pageable)
            .map { it.toDomain() }

    override fun findRootCategoryByDisplayId(displayId: DisplayId): DisplayCategoryContext? =
        displayCategoryContextMappingEntityRepository
            .findByDisplayIdAndParentId(
                displayId = displayId.value,
                parentId = null,
            )?.let { it.toDomain() }
}
