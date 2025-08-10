package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.GetDisplayUseCase
import com.musinsa.assignment.category.application.port.output.DisplayOutputPort
import com.musinsa.assignment.category.domain.Display
import com.musinsa.assignment.category.domain.DisplayId
import com.musinsa.assignment.category.domain.exceptions.DisplayNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class DisplayQueryService(
    private val displayOutputPort: DisplayOutputPort,
) : GetDisplayUseCase {
    override fun getDisplayById(id: DisplayId): Display =
        displayOutputPort.findById(id) ?: throw DisplayNotFoundException("Display with id ${id.value} not found")

    override fun getAllDisplays(pageable: Pageable): Page<Display> = displayOutputPort.findAll(pageable)
}
