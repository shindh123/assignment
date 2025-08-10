package com.musinsa.assignment.category.adapter.output.persistence

import com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper.toDomain
import com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper.toEntity
import com.musinsa.assignment.category.adapter.output.persistence.jpa.repository.DisplayEntityRepository
import com.musinsa.assignment.category.application.port.output.DisplayOutputPort
import com.musinsa.assignment.category.domain.Display
import com.musinsa.assignment.category.domain.DisplayId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class DisplayPersistenceAdapter(
    private val displayEntityRepository: DisplayEntityRepository,
) : DisplayOutputPort {
    override fun save(display: Display): Display {
        val saved = displayEntityRepository.save(display.toEntity())
        return saved.toDomain()
    }

    override fun delete(id: DisplayId) {
        displayEntityRepository.deleteById(id.value)
    }

    override fun findById(id: DisplayId): Display? = displayEntityRepository.findByIdOrNull(id.value)?.let { it.toDomain() }

    override fun findAll(pageable: Pageable): Page<Display> = displayEntityRepository.findAll(pageable).map { it.toDomain() }

    override fun findByName(name: String): Display? = displayEntityRepository.findByName(name)?.let { return it.toDomain() }
}
