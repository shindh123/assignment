package com.musinsa.assignment.category.domain

data class DisplayCategoryContext(
    val id: DisplayCategoryContextId? = null,
    val displayId: DisplayId,
    val categoryId: CategoryId,
    val parentId: CategoryId?,
    val order: Int,
) {
    fun updateOrder(newOrder: Int): DisplayCategoryContext = this.copy(order = newOrder)

    fun isRoot(): Boolean = parentId == null

    fun hasValidParent(): Boolean = parentId == null || parentId != categoryId
}
