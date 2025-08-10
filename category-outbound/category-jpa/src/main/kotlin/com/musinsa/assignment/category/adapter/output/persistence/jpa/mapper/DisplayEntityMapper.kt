package com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper

import com.musinsa.assignment.category.adapter.output.persistence.jpa.entity.DisplayEntity
import com.musinsa.assignment.category.domain.Display
import com.musinsa.assignment.category.domain.DisplayId

internal fun Display.toEntity(): DisplayEntity =
    DisplayEntity(
        id = this.id?.value,
        name = this.name,
        description = this.description,
    )

internal fun DisplayEntity.toDomain(): Display =
    Display(
        id = this.id?.let { DisplayId(it) },
        name = this.name,
        description = this.description,
    )
