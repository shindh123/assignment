package com.musinsa.assignment.category.adapter.input.web

import com.musinsa.assignment.category.adapter.input.web.presenter.toViewModel
import com.musinsa.assignment.category.adapter.input.web.request.CreateDisplayAdminRequest
import com.musinsa.assignment.category.adapter.input.web.request.UpdateDisplayAdminRequest
import com.musinsa.assignment.category.adapter.input.web.response.DisplayAdminViewModel
import com.musinsa.assignment.category.application.port.input.CreateDisplayUseCase
import com.musinsa.assignment.category.application.port.input.DeleteDisplayUseCase
import com.musinsa.assignment.category.application.port.input.GetDisplayUseCase
import com.musinsa.assignment.category.application.port.input.UpdateDisplayUseCase
import com.musinsa.assignment.category.domain.DisplayId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class DisplayAdminControllerImpl(
    private val createDisplayUseCase: CreateDisplayUseCase,
    private val updateDisplayUseCase: UpdateDisplayUseCase,
    private val deleteDisplayUseCase: DeleteDisplayUseCase,
    private val getDisplayUseCase: GetDisplayUseCase,
) : DisplayAdminController {
    override fun create(request: CreateDisplayAdminRequest): ResponseEntity<DisplayAdminViewModel> {
        val result =
            createDisplayUseCase.create(
                command =
                    CreateDisplayUseCase.CreateDisplayCommand(
                        name = request.name,
                        description = request.description,
                    ),
            )
        return ResponseEntity
            .created(URI.create("/admin/displays/${result.id}"))
            .body(result.toViewModel())
    }

    override fun findById(id: Long): ResponseEntity<DisplayAdminViewModel> {
        val result = getDisplayUseCase.getDisplayById(id = DisplayId(id))
        return ResponseEntity.ok(result.toViewModel())
    }

    override fun findDisplaysPage(pageable: Pageable): ResponseEntity<Page<DisplayAdminViewModel>> {
        val result = getDisplayUseCase.getAllDisplays(pageable)
        return ResponseEntity.ok(result.map { it.toViewModel() })
    }

    override fun update(
        id: Long,
        body: UpdateDisplayAdminRequest,
    ): ResponseEntity<DisplayAdminViewModel> {
        val result =
            updateDisplayUseCase.update(
                command =
                    UpdateDisplayUseCase.UpdateDisplayCommand(
                        id = DisplayId(id),
                        name = body.name,
                        description = body.description,
                    ),
            )
        return ResponseEntity.ok(result.toViewModel())
    }

    override fun delete(id: Long): ResponseEntity<Unit> {
        deleteDisplayUseCase.delete(command = DeleteDisplayUseCase.DeleteDisplayCommand(id = DisplayId(id)))
        return ResponseEntity.noContent().build()
    }
}
