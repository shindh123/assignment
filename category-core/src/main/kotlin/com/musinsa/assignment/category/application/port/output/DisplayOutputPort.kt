package com.musinsa.assignment.category.application.port.output

import com.musinsa.assignment.category.domain.Display
import com.musinsa.assignment.category.domain.DisplayId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface DisplayOutputPort {
    fun save(display: Display): Display

    fun delete(id: DisplayId)

    fun findById(id: DisplayId): Display?

    fun findAll(pageable: Pageable = Pageable.unpaged()): Page<Display>

    fun findByName(name: String): Display?
}
