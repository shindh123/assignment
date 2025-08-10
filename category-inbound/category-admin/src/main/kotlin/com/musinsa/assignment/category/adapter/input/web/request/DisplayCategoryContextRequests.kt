package com.musinsa.assignment.category.adapter.input.web.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

data class AssignCategoryToDisplayRequest(
    @get:Schema(description = "카테고리 ID", example = "1", required = true)
    val categoryId: Long,
    @get:Schema(description = "부모 카테고리 ID (없으면 root)", example = "1", nullable = true)
    val parentId: Long? = null,
    @get:Min(0)
    @get:Schema(description = "표시 순서", example = "0", defaultValue = "0")
    val order: Int = 0,
)

data class UpdateCategoryDisplayMappingRequest(
    @get:Schema(description = "전시 ID", example = "1", required = true)
    val displayId: Long,
    @get:Schema(description = "카테고리 ID", example = "1", required = true)
    val categoryId: Long,
    @get:Schema(description = "부모 카테고리 ID (없으면 root)", example = "1", nullable = true)
    val parentId: Long? = null,
    @get:Min(0)
    @get:Schema(description = "표시 순서", example = "0")
    val order: Int,
)
