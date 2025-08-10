package com.musinsa.assignment.category.adapter.input.web.presenter

import com.musinsa.assignment.category.adapter.input.web.response.DisplayCategoryContextAdminViewModel
import com.musinsa.assignment.category.adapter.output.persistence.jpa.projection.DisplayCategoryContextProjection
import com.musinsa.assignment.category.domain.DisplayCategoryContext

fun DisplayCategoryContext.toViewModel(): DisplayCategoryContextAdminViewModel =
    DisplayCategoryContextAdminViewModel(
        id = this.id?.value ?: 0,
        displayId = this.displayId.value,
        categoryId = this.categoryId.value,
        parentId = this.parentId?.value,
        order = this.order,
    )

fun DisplayCategoryContextProjection.toViewModel(): DisplayCategoryContextAdminViewModel =
    DisplayCategoryContextAdminViewModel(
        id = this.mappingId,
        displayId = this.displayId,
        categoryId = this.categoryId,
        parentId = this.parentId,
        order = this.ordering,
    )
