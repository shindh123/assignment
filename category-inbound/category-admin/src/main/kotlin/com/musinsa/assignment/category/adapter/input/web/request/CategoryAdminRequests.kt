package com.musinsa.assignment.category.adapter.input.web.request

import io.swagger.v3.oas.annotations.media.Schema

data class CreateCategoryAdminRequest(
    @get:Schema(description = "카테고리 이름", example = "의류", required = true)
    val name: String,
)

data class RenameCategoryAdminRequest(
    @get:Schema(description = "변경할 카테고리 이름", example = "패션", required = true)
    val name: String,
)
