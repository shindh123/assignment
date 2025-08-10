package com.musinsa.assignment.category.adapter.input.web.response

import io.swagger.v3.oas.annotations.media.Schema

data class DisplayAdminViewModel(
    @field:Schema(description = "전시 ID", example = "1")
    val id: Long,
    @field:Schema(description = "전시 이름", example = "메인 전시")
    val name: String,
    @field:Schema(description = "전시 설명", example = "메인 페이지에 노출되는 카테고리 전시입니다", nullable = true)
    val description: String?,
)
