package com.musinsa.assignment.category.adapter.input.web

import com.musinsa.assignment.category.adapter.input.web.request.CreateCategoryAdminRequest
import com.musinsa.assignment.category.adapter.input.web.request.RenameCategoryAdminRequest
import com.musinsa.assignment.category.adapter.input.web.response.CategoryAdminViewModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Tag(
    name = "Category Admin",
    description = "관리자용 카테고리 관리 API",
)
@RequestMapping("/admin/categories")
interface CategoryAdminController {
    @Operation(
        summary = "카테고리 생성",
        description = "이름을 받아 새로운 카테고리를 생성합니다. 이름은 유니크해야 합니다.",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "생성됨",
                content = [Content(schema = Schema(implementation = CategoryAdminViewModel::class))],
            ),
            ApiResponse(responseCode = "500", description = "서버 내부 오류 발생"),
        ],
    )
    @PostMapping
    fun create(
        @RequestBody request: CreateCategoryAdminRequest,
    ): ResponseEntity<CategoryAdminViewModel>

    @Operation(
        summary = "카테고리 단건 조회",
        description = "ID로 카테고리를 조회합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = CategoryAdminViewModel::class))],
            ),
            ApiResponse(responseCode = "404", description = "없음"),
        ],
    )
    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: Long,
    ): ResponseEntity<CategoryAdminViewModel>

    @Operation(
        summary = "카테고리 목록 조회",
        description = "카테고리 목록을 페이지 단위로 조회합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = Page::class))],
            ),
        ],
    )
    @GetMapping
    fun findCategoriesPage(
        @PageableDefault(page = 0, size = 10) pageable: Pageable,
    ): ResponseEntity<Page<CategoryAdminViewModel>>

    @Operation(
        summary = "카테고리 이름 변경",
        description = "카테고리의 이름을 변경합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = CategoryAdminViewModel::class))],
            ),
            ApiResponse(responseCode = "404", description = "없음"),
            ApiResponse(responseCode = "500", description = "서버 내부 오류 발생"),
        ],
    )
    @PatchMapping("/{id}")
    fun rename(
        @PathVariable id: Long,
        @RequestBody request: RenameCategoryAdminRequest,
    ): ResponseEntity<CategoryAdminViewModel>

    @Operation(
        summary = "카테고리 삭제",
        description = "카테고리를 삭제합니다.",
        responses = [
            ApiResponse(responseCode = "204", description = "삭제됨"),
            ApiResponse(responseCode = "404", description = "없음"),
        ],
    )
    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ): ResponseEntity<Unit>
}
