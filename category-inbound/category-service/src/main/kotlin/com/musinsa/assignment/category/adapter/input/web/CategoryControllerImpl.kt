package com.musinsa.assignment.category.adapter.input.web

import com.musinsa.assignment.category.adapter.input.web.presenter.toViewModel
import com.musinsa.assignment.category.adapter.input.web.response.DisplayCategoryGroupViewModel
import com.musinsa.assignment.category.application.port.input.LoadDisplayCategoryGroupUseCase
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryControllerImpl(
    private val loadDisplayCategoryGroupUseCase: LoadDisplayCategoryGroupUseCase,
) : CategoryController {
    override fun getCategorySubtree(
        displayId: Long,
        categoryId: Long?,
    ): ResponseEntity<DisplayCategoryGroupViewModel> {
        val displayCategoryGroup =
            loadDisplayCategoryGroupUseCase.loadDisplayCategoryGroupByDisplayId(
                query =
                    LoadDisplayCategoryGroupUseCase.DisplayCategoryGroupQuery(
                        displayId = DisplayId(displayId),
                        rootId = categoryId?.let { CategoryId(it) },
                    ),
            )
        return ResponseEntity.ok(displayCategoryGroup.toViewModel())
    }
}
