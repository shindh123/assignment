package com.musinsa.assignment.category.adapter.input.web

import com.musinsa.assignment.category.adapter.input.web.request.CreateDisplayAdminRequest
import com.musinsa.assignment.category.adapter.input.web.request.UpdateDisplayAdminRequest
import com.musinsa.assignment.category.adapter.input.web.response.DisplayAdminViewModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
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
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Tag(
    name = "Display Admin",
    description = "관리자용 '전시(Display)' 관리 API",
)
@RequestMapping("/admin/displays")
interface DisplayAdminController {
    @Operation(
        summary = "전시 생성",
        description = "이름과 설명으로 전시(Display)를 생성합니다. 이름은 유니크해야 합니다.",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "생성됨",
                content = [Content(schema = Schema(implementation = DisplayAdminViewModel::class))],
            ),
            ApiResponse(responseCode = "500", description = "서버 내부 오류 발생"),
            ApiResponse(responseCode = "400", description = "유효성 오류"),
        ],
    )
    @PostMapping
    fun create(
        @RequestBody request: CreateDisplayAdminRequest,
    ): ResponseEntity<DisplayAdminViewModel>

    @Operation(
        summary = "전시 단건 조회",
        description = "ID로 전시를 조회합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = DisplayAdminViewModel::class))],
            ),
            ApiResponse(responseCode = "404", description = "없음"),
        ],
    )
    @GetMapping("/{id}")
    fun findById(
        @Parameter(description = "전시(Display) ID", example = "1")
        @PathVariable id: Long,
    ): ResponseEntity<DisplayAdminViewModel>

    @Operation(
        summary = "전시 목록 조회",
        description = "전시 목록을 페이지 단위로 조회합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = Page::class))],
            ),
        ],
    )
    @GetMapping
    fun findDisplaysPage(
        @PageableDefault(page = 0, size = 10) pageable: Pageable,
    ): ResponseEntity<Page<DisplayAdminViewModel>>

    @Operation(
        summary = "전시 수정",
        description = "전시를 전체 수정합니다",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [Content(schema = Schema(implementation = DisplayAdminViewModel::class))],
            ),
            ApiResponse(responseCode = "404", description = "없음"),
            ApiResponse(responseCode = "500", description = "서버 내부 오류 발생"),
        ],
    )
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody body: UpdateDisplayAdminRequest,
    ): ResponseEntity<DisplayAdminViewModel>

    @Operation(
        summary = "전시 삭제",
        description = "전시를 삭제합니다.",
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
