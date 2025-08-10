package com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper

import com.musinsa.assignment.category.adapter.output.persistence.jpa.projection.DisplayCategoryContextProjection
import com.musinsa.assignment.category.domain.Category
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.exceptions.CategoryNotFoundException
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryNode

internal fun DisplayCategoryContextProjection.toDomain(): DisplayCategoryNode =
    DisplayCategoryNode(
        category =
            Category(
                id = CategoryId(this.categoryId),
                name = this.categoryName,
            ),
        depth = this.depth,
        order = this.ordering,
        parentId = this.parentId?.let { CategoryId(it) },
    )

internal fun List<DisplayCategoryContextProjection>.buildReadModel(): DisplayCategoryNode {
    val categoryMapByCategoryId =
        this.associateBy { CategoryId(it.categoryId) }.mapValues {
            DisplayCategoryNode(
                category =
                    Category(
                        id = CategoryId(it.value.categoryId),
                        name = it.value.categoryName,
                    ),
                depth = it.value.depth,
                order = it.value.ordering,
                parentId = it.value.parentId?.let { CategoryId(it) },
            )
        }
    this.forEach { me ->
        val categoryId = CategoryId(me.categoryId)
        val parentId = me.parentId?.let { CategoryId(it) }
        val parentCategoryReadModel = categoryMapByCategoryId[parentId]
        val categoryReadModel =
            categoryMapByCategoryId[categoryId] ?: throw CategoryNotFoundException("Category [$me] not found")
        // parent 가 있으면 나를 자식으로 추가
        parentCategoryReadModel?.addChild(categoryReadModel)
    }
    return findRoot(categoryMapByCategoryId)
}

/**
 * parent가 없는 노드가 root 로 설정한다.
 */
private fun List<DisplayCategoryContextProjection>.findRoot(
    categoryMapByCategoryId: Map<CategoryId, DisplayCategoryNode>,
): DisplayCategoryNode {
    val root =
        this
            .filter { it.parentId?.let { pid -> categoryMapByCategoryId[CategoryId(pid)] } == null }
            .map { categoryMapByCategoryId[CategoryId(it.categoryId)] }
            .firstOrNull()
            ?: throw CategoryNotFoundException("No root category found")
    return root
}
