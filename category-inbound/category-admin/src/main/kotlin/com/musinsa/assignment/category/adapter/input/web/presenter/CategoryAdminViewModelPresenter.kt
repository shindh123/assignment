package com.musinsa.assignment.category.adapter.input.web.presenter

import com.musinsa.assignment.category.adapter.input.web.response.CategoryAdminViewModel
import com.musinsa.assignment.category.domain.Category

internal fun Category.toViewModel(): CategoryAdminViewModel =
    CategoryAdminViewModel(
        id = requireNotNull(this.id?.value) { "Category ID must not be null" },
        name = this.name,
    )
