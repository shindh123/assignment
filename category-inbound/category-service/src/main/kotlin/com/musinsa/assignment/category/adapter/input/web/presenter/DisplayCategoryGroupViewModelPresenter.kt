package com.musinsa.assignment.category.adapter.input.web.presenter

import com.musinsa.assignment.category.adapter.input.web.response.DisplayCategoryGroupViewModel
import com.musinsa.assignment.category.adapter.input.web.response.DisplayCategoryGroupViewModel.DisplayCategoryNodeViewModel
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryGroup
import com.musinsa.assignment.category.domain.readmodel.DisplayCategoryNode

internal fun DisplayCategoryGroup.toViewModel(): DisplayCategoryGroupViewModel =
    DisplayCategoryGroupViewModel(
        displayId = requireNotNull(display.id?.value),
        displayName = display.name,
        root = categoryRoot.toViewModel(),
    )

internal fun DisplayCategoryNode.toViewModel(): DisplayCategoryNodeViewModel =
    DisplayCategoryNodeViewModel(
        categoryId = requireNotNull(id()?.value),
        depth = depth,
        order = order,
        categoryName = category.name,
        children = children.map { it.toViewModel() },
    )
