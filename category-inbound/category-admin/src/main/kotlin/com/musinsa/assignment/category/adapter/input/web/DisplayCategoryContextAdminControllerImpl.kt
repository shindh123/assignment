package com.musinsa.assignment.category.adapter.input.web

import com.musinsa.assignment.category.adapter.input.web.presenter.toViewModel
import com.musinsa.assignment.category.adapter.input.web.request.AssignCategoryToDisplayRequest
import com.musinsa.assignment.category.adapter.input.web.request.UpdateCategoryDisplayMappingRequest
import com.musinsa.assignment.category.adapter.input.web.response.DisplayCategoryContextAdminViewModel
import com.musinsa.assignment.category.application.port.input.AssignCategoryToDisplayUseCase
import com.musinsa.assignment.category.application.port.input.DeleteCategoryFromDisplayUseCase
import com.musinsa.assignment.category.application.port.input.GetCategoryDisplayMappingUseCase
import com.musinsa.assignment.category.application.port.input.UpdateDisplayCategoryContextUseCase
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryContextId
import com.musinsa.assignment.category.domain.DisplayId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class DisplayCategoryContextAdminControllerImpl(
    private val assignCategoryToDisplayUseCase: AssignCategoryToDisplayUseCase,
    private val getCategoryDisplayMappingUseCase: GetCategoryDisplayMappingUseCase,
    private val updateDisplayCategoryContextUseCase: UpdateDisplayCategoryContextUseCase,
    private val deleteCategoryFromDisplayUseCase: DeleteCategoryFromDisplayUseCase,
) : DisplayCategoryContextAdminController {
    override fun assignCategoryToDisplay(
        displayId: Long,
        request: AssignCategoryToDisplayRequest,
    ): ResponseEntity<DisplayCategoryContextAdminViewModel> {
        val result =
            assignCategoryToDisplayUseCase.assignCategoryToDisplay(
                command =
                    AssignCategoryToDisplayUseCase.AssignCategoryToDisplayCommand(
                        displayId = DisplayId(displayId),
                        categoryId = CategoryId(request.categoryId),
                        parentId = request.parentId?.let { CategoryId(it) },
                        order = request.order,
                    ),
            )
        return ResponseEntity
            .created(URI.create("/admin/displays/$displayId/categories/${result.categoryId.value}"))
            .body(result.toViewModel())
    }

    override fun findMappingById(
        displayId: Long,
        categoryId: Long,
    ): ResponseEntity<DisplayCategoryContextAdminViewModel> {
        val result =
            getCategoryDisplayMappingUseCase.getMapping(
                query =
                    GetCategoryDisplayMappingUseCase.GetCategoryDisplayMappingQuery(
                        displayId = DisplayId(displayId),
                        categoryId = CategoryId(categoryId),
                    ),
            )
        return ResponseEntity.ok(result.toViewModel())
    }

    override fun findDisplayCategoryMappingsPage(
        displayId: Long,
        pageable: Pageable,
    ): ResponseEntity<Page<DisplayCategoryContextAdminViewModel>> {
        val result =
            getCategoryDisplayMappingUseCase.getMappings(
                query =
                    GetCategoryDisplayMappingUseCase.GetCategoryDisplayMappingsQuery(
                        displayId = DisplayId(displayId),
                        pageable = pageable,
                    ),
            )
        val viewModels = result.map { it.toViewModel() }

        return ResponseEntity.ok(viewModels)
    }

    override fun updateMapping(
        mappingId: Long,
        request: UpdateCategoryDisplayMappingRequest,
    ): ResponseEntity<DisplayCategoryContextAdminViewModel> {
        val result =
            updateDisplayCategoryContextUseCase.update(
                command =
                    UpdateDisplayCategoryContextUseCase.UpdateDisplayCategoryContextCommand(
                        displayId = DisplayId(request.displayId),
                        categoryId = CategoryId(request.categoryId),
                        parentId = request.parentId?.let { CategoryId(it) },
                        order = request.order,
                        id = DisplayCategoryContextId(mappingId),
                    ),
            )
        return ResponseEntity.ok(result.toViewModel())
    }

    override fun removeCategoryFromDisplay(
        displayId: Long,
        categoryId: Long,
    ): ResponseEntity<Unit> {
        deleteCategoryFromDisplayUseCase.delete(
            command =
                DeleteCategoryFromDisplayUseCase.DeleteCategoryFromDisplayCommand(
                    displayId = DisplayId(displayId),
                    categoryId = CategoryId(categoryId),
                ),
        )
        return ResponseEntity.noContent().build()
    }
}
