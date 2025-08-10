package com.musinsa.assignment.category.adapter.output.persistence.jpa.repository

import com.musinsa.assignment.category.adapter.output.persistence.jpa.entity.DisplayCategoryContextMappingEntity
import com.musinsa.assignment.category.adapter.output.persistence.jpa.projection.DisplayCategoryContextProjection
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DisplayCategoryContextMappingEntityRepository : JpaRepository<DisplayCategoryContextMappingEntity, Long> {
    @Query(
        value =
            """
            WITH RECURSIVE tree(mapping_id, display_id, category_id, parent_id, depth, ordering) AS (
              SELECT context.id,context.display_id, context.category_id, context.parent_id, 0, context.ordering
              FROM display_category_context_mappings context 
              WHERE context.category_id = :rootId
              AND context.display_id = :displayId
              UNION ALL
              SELECT context.id,context.display_id, context.category_id, context.parent_id, t.depth + 1, context.ordering 
              FROM display_category_context_mappings context 
              INNER JOIN tree t ON context.parent_id = t.category_id AND context.display_id = t.display_id
            )
            SELECT mapping_id, display_id, category_id, parent_id, depth, ordering, category.name as category_name FROM tree t 
            INNER JOIN categories category ON category.id = t.category_id
            ORDER BY depth, ordering 
            """,
        nativeQuery = true,
    )
    fun findSubTree(
        rootId: Long,
        displayId: Long,
    ): List<DisplayCategoryContextProjection>

    fun findByDisplayId(displayId: Long): List<DisplayCategoryContextMappingEntity>

    fun findByDisplayIdAndParentId(
        displayId: Long,
        parentId: Long?,
    ): DisplayCategoryContextMappingEntity?

    fun findByCategoryId(categoryId: Long): List<DisplayCategoryContextMappingEntity>

    fun findByDisplayIdAndCategoryId(
        displayId: Long,
        categoryId: Long,
    ): DisplayCategoryContextMappingEntity?

    fun findAllByDisplayId(
        displayId: Long,
        pageable: Pageable,
    ): Page<DisplayCategoryContextMappingEntity>

    fun deleteByDisplayIdAndCategoryId(
        displayId: Long,
        categoryId: Long,
    )
}
