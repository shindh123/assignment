package com.musinsa.assignment.category.domain.readmodel

import com.musinsa.assignment.category.domain.Category
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.extensions.containsNot

data class DisplayCategoryNode(
    val category: Category,
    val parentId: CategoryId? = null,
    val depth: Int = 0,
    val order: Int = 0,
    val children: MutableList<DisplayCategoryNode> = mutableListOf(),
) {
    fun id() = category.id

    fun findById(id: CategoryId): DisplayCategoryNode? =
        when {
            this.id() == id -> this
            else -> children.firstNotNullOfOrNull { it.findById(id) }
        }

    fun isLeaf(): Boolean = children.isEmpty()

    fun hasChild(childId: CategoryId): Boolean = children.any { it.id() == childId }

    fun size(): Int = 1 + children.sumOf { it.size() }

    fun directChildrenCount(): Int = children.size

    fun sortedChildren() = children.sortedBy { it.order }

    fun addChild(child: DisplayCategoryNode) {
        if (children.containsNot(child)) {
            children += child
        }
    }
}
