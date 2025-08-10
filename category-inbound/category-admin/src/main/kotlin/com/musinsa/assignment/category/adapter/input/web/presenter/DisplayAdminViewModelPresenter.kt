package com.musinsa.assignment.category.adapter.input.web.presenter

import com.musinsa.assignment.category.adapter.input.web.response.DisplayAdminViewModel
import com.musinsa.assignment.category.domain.Display

internal fun Display.toViewModel(): DisplayAdminViewModel =
    DisplayAdminViewModel(
        id = requireNotNull(this.id?.value) { "Display ID must not be null" },
        name = this.name,
        description = this.description,
    )
