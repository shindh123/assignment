package com.musinsa.assignment.category.adapter.output.persistence.jpa.projection

interface DisplayCategoryContextProjection {
    val mappingId: Long
    val displayId: Long
    val categoryId: Long
    val parentId: Long?
    val depth: Int
    val ordering: Int
    val categoryName: String
}
