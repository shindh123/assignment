package com.musinsa.assignment.category.application.port.input

import com.musinsa.assignment.category.domain.Display
import com.musinsa.assignment.category.domain.DisplayId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetDisplayUseCase {
    fun getDisplayById(id: DisplayId): Display

    fun getAllDisplays(pageable: Pageable): Page<Display>
}
