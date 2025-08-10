package com.musinsa.assignment.category.adapter.input.web

import com.musinsa.assignment.category.adapter.input.web.presenter.toViewModel
import com.musinsa.assignment.category.adapter.input.web.request.CreateCategoryAdminRequest
import com.musinsa.assignment.category.adapter.input.web.request.RenameCategoryAdminRequest
import com.musinsa.assignment.category.adapter.input.web.response.CategoryAdminViewModel
import com.musinsa.assignment.category.application.port.input.CreateCategoryUseCase
import com.musinsa.assignment.category.application.port.input.DeleteCategoryUseCase
import com.musinsa.assignment.category.application.port.input.GetCategoryUseCase
import com.musinsa.assignment.category.application.port.input.RenameCategoryUseCase
import com.musinsa.assignment.category.domain.CategoryId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class CategoryAdminControllerImpl(
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val renameCategoryUseCase: RenameCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
) : CategoryAdminController {
    override fun create(request: CreateCategoryAdminRequest): ResponseEntity<CategoryAdminViewModel> {
        val result =
            createCategoryUseCase.create(command = CreateCategoryUseCase.CreateCategoryCommand(name = request.name))
        return ResponseEntity
            .created(URI.create("/admin/categories/${result.id}"))
            .body(result.toViewModel())
    }

    override fun findById(id: Long): ResponseEntity<CategoryAdminViewModel> {
        val result = getCategoryUseCase.getCategoryById(CategoryId(id))
        return ResponseEntity.ok(result.toViewModel())
    }

    override fun findCategoriesPage(pageable: Pageable): ResponseEntity<Page<CategoryAdminViewModel>> {
        val result = getCategoryUseCase.getAllCategories(pageable)
        return ResponseEntity.ok(result.map { it.toViewModel() })
    }

    override fun rename(
        id: Long,
        request: RenameCategoryAdminRequest,
    ): ResponseEntity<CategoryAdminViewModel> {
        val result =
            renameCategoryUseCase.rename(
                command =
                    RenameCategoryUseCase.RenameCategoryCommand(
                        id = CategoryId(id),
                        name = request.name,
                    ),
            )
        return ResponseEntity.ok(result.toViewModel())
    }

    override fun delete(id: Long): ResponseEntity<Unit> {
        deleteCategoryUseCase.delete(command = DeleteCategoryUseCase.DeleteCategoryCommand(id = CategoryId(id)))
        return ResponseEntity.noContent().build()
    }
}
