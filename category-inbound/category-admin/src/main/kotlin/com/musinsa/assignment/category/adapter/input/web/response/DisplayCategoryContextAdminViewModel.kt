package com.musinsa.assignment.category.adapter.input.web.response

import io.swagger.v3.oas.annotations.media.Schema

data class DisplayCategoryContextAdminViewModel(
    @field:Schema(description = "매핑 ID", example = "1")
    val id: Long,
    @field:Schema(description = "전시 ID", example = "1")
    val displayId: Long,
    @field:Schema(description = "카테고리 ID", example = "1")
    val categoryId: Long,
    @field:Schema(description = "부모 카테고리 ID", example = "2", nullable = true)
    val parentId: Long?,
    @field:Schema(description = "표시 순서", example = "0")
    val order: Int,
)
