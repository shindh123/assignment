package com.musinsa.assignment.category.adapter.input.web.response

import io.swagger.v3.oas.annotations.media.Schema

data class CategoryAdminViewModel(
    @field:Schema(description = "카테고리 ID", example = "1")
    val id: Long,
    @field:Schema(description = "카테고리 이름", example = "의류")
    val name: String,
)
