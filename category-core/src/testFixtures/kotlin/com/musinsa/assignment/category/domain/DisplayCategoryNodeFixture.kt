package com.musinsa.assignment.category.domain

import com.appmattus.kotlinfixture.kotlinFixture
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryNode

object DisplayCategoryNodeFixture {
    val fixture = kotlinFixture()

    fun displayCategoryNode(
        category: Category,
        depth: Int = 0,
        order: Int = 0,
        children: MutableList<DisplayCategoryNode> = mutableListOf(),
    ) = fixture<DisplayCategoryNode> {
        property(DisplayCategoryNode::category) { category }
        property(DisplayCategoryNode::depth) { depth }
        property(DisplayCategoryNode::order) { order }
        property(DisplayCategoryNode::children) { children }
    }

    fun displayCategoryNode() = fixture<DisplayCategoryNode>()
}
