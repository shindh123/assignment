package com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper

import com.musinsa.assignment.category.adapter.output.persistence.jpa.entity.CategoryEntity
import com.musinsa.assignment.category.domain.Category
import com.musinsa.assignment.category.domain.CategoryId

internal fun Category.toEntity(): CategoryEntity =
    CategoryEntity(
        id = this.id?.value,
        name = this.name,
    )

internal fun CategoryEntity.toDomain(): Category =
    Category(
        id = this.id?.let { CategoryId(it) },
        name = this.name,
    )
