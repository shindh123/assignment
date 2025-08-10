package com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper

import com.musinsa.assignment.category.adapter.output.persistence.jpa.entity.DisplayCategoryContextMappingEntity
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryContext
import com.musinsa.assignment.category.domain.DisplayCategoryContextId
import com.musinsa.assignment.category.domain.DisplayId

internal fun DisplayCategoryContext.toEntity(): DisplayCategoryContextMappingEntity =
    DisplayCategoryContextMappingEntity(
        id = this.id?.value,
        categoryId = this.categoryId.value,
        displayId = this.displayId.value,
        parentId = this.parentId?.value,
        ordering = this.order,
    )

internal fun DisplayCategoryContextMappingEntity.toDomain(): DisplayCategoryContext =
    DisplayCategoryContext(
        id = this.id?.let { DisplayCategoryContextId(it) },
        categoryId = CategoryId(this.categoryId),
        displayId = DisplayId(this.displayId),
        parentId = this.parentId?.let { CategoryId(it) },
        order = this.ordering,
    )
