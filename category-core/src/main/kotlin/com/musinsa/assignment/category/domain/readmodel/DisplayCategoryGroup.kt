package com.musinsa.assignment.category.domain.readmodel

import com.musinsa.assignment.category.domain.Display

data class DisplayCategoryGroup(
    val display: Display,
    val categoryRoot: DisplayCategoryNode,
)
