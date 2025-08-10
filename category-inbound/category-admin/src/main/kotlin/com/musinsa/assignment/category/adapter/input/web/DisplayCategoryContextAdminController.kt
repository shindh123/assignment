package com.musinsa.assignment.category.adapter.input.web

import com.musinsa.assignment.category.adapter.input.web.request.AssignCategoryToDisplayRequest
import com.musinsa.assignment.category.adapter.input.web.request.UpdateCategoryDisplayMappingRequest
import com.musinsa.assignment.category.adapter.input.web.response.DisplayCategoryContextAdminViewModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Tag(
    name = "Display Category Mapping Admin",
    description = "관리자용 전시-카테고리 매핑 관리 API",
)
@RequestMapping("/admin/mappings")
interface DisplayCategoryContextAdminController {
    @Operation(
        summary = "카테고리를 전시에 할당",
        description = "특정 전시에 카테고리를 할당합니다. 계층구조를 위한 부모 카테고리와 순서를 지정할 수 있습니다.",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "생성됨",
                content = [Content(schema = Schema(implementation = DisplayCategoryContextAdminViewModel::class))],
            ),
            ApiResponse(responseCode = "404", description = "전시 또는 카테고리 없음"),
            ApiResponse(responseCode = "400", description = "유효성 오류"),
        ],
    )
    @PostMapping("/displays/{displayId}/categories")
    fun assignCategoryToDisplay(
        @Parameter(description = "전시 ID", example = "1")
        @PathVariable displayId: Long,
        @Valid @RequestBody request: AssignCategoryToDisplayRequest,
    ): ResponseEntity<DisplayCategoryContextAdminViewModel>

    @Operation(
        summary = "전시-카테고리 매핑 단건 조회",
        description = "특정 전시에 할당된 카테고리 매핑 정보를 조회합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = DisplayCategoryContextAdminViewModel::class))],
            ),
            ApiResponse(responseCode = "404", description = "매핑 없음"),
        ],
    )
    @GetMapping("/displays/{displayId}/categories/{categoryId}")
    fun findMappingById(
        @Parameter(description = "전시 ID", example = "1")
        @PathVariable displayId: Long,
        @Parameter(description = "카테고리 ID", example = "1")
        @PathVariable categoryId: Long,
    ): ResponseEntity<DisplayCategoryContextAdminViewModel>

    @Operation(
        summary = "전시에 할당된 카테고리 목록 조회",
        description = "특정 전시에 할당된 모든 카테고리 매핑 정보를 페이지 단위로 조회합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = Page::class))],
            ),
            ApiResponse(responseCode = "404", description = "전시 없음"),
        ],
    )
    @GetMapping("/displays/{displayId}/categories")
    fun findDisplayCategoryMappingsPage(
        @Parameter(description = "전시 ID", example = "1")
        @PathVariable displayId: Long,
        @PageableDefault(page = 0, size = 10) pageable: Pageable,
    ): ResponseEntity<Page<DisplayCategoryContextAdminViewModel>>

    @Operation(
        summary = "전시-카테고리 매핑 정보 수정",
        description = "카테고리-전시 매핑 정보(부모 카테고리, 순서, 깊이)를 수정합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = DisplayCategoryContextAdminViewModel::class))],
            ),
            ApiResponse(responseCode = "404", description = "매핑 없음"),
            ApiResponse(responseCode = "400", description = "유효성 오류"),
        ],
    )
    @PutMapping("/{mappingId}")
    fun updateMapping(
        @Parameter(description = "매핑 ID", example = "1")
        @PathVariable mappingId: Long,
        @RequestBody request: UpdateCategoryDisplayMappingRequest,
    ): ResponseEntity<DisplayCategoryContextAdminViewModel>

    @Operation(
        summary = "전시에서 카테고리 제거",
        description = "특정 전시에서 카테고리를 제거합니다. 이 작업은 카테고리 자체를 삭제하지 않고 매핑만 제거합니다.",
        responses = [
            ApiResponse(responseCode = "204", description = "삭제됨"),
            ApiResponse(responseCode = "404", description = "매핑 없음"),
        ],
    )
    @DeleteMapping("/displays/{displayId}/categories/{categoryId}")
    fun removeCategoryFromDisplay(
        @Parameter(description = "전시 ID", example = "1")
        @PathVariable displayId: Long,
        @Parameter(description = "카테고리 ID", example = "1")
        @PathVariable categoryId: Long,
    ): ResponseEntity<Unit>
}
