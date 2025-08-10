package com.musinsa.assignment.category.adapter.input.web.request

import io.swagger.v3.oas.annotations.media.Schema

data class CreateDisplayAdminRequest(
    @get:Schema(description = "전시 이름", example = "메인 전시", required = true)
    val name: String,
    @get:Schema(description = "전시 설명", example = "메인 페이지에 노출되는 카테고리 전시입니다", nullable = true)
    val description: String? = null,
)

data class UpdateDisplayAdminRequest(
    @get:Schema(description = "변경할 전시 이름", example = "메인 전시 (수정)", required = true)
    val name: String,
    @get:Schema(description = "변경할 전시 설명", example = "메인 페이지에 노출되는 수정된 카테고리 전시입니다", nullable = true)
    val description: String? = null,
)
