package com.musinsa.assignment.category.adapter.input.web

import com.musinsa.assignment.category.adapter.input.web.response.DisplayCategoryGroupViewModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Tag(
    name = "Display Categories",
    description = "전시 카테고리 조회 API",
)
@RequestMapping("/api/v1/displays/{displayId}/categories")
interface CategoryController {
    @Operation(
        summary = "카테고리 트리 조회",
        description = "특정 전시에 할당된 카테고리 계층 구조를 조회합니다. 카테고리 ID를 지정하면 해당 카테고리부터의 하위 트리를 반환합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = DisplayCategoryGroupViewModel::class))],
            ),
            ApiResponse(responseCode = "404", description = "전시 또는 카테고리 없음"),
        ],
    )
    @GetMapping
    fun getCategorySubtree(
        @Parameter(description = "전시 ID", example = "1", required = true)
        @PathVariable displayId: Long,
        @Parameter(description = "카테고리 ID (없으면 전체 트리 조회)", example = "1")
        @RequestParam(required = false) categoryId: Long? = null,
    ): ResponseEntity<DisplayCategoryGroupViewModel>
}
