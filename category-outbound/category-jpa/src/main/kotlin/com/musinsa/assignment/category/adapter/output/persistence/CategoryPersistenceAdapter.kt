package com.musinsa.assignment.category.adapter.output.persistence

import com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper.toDomain
import com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper.toEntity
import com.musinsa.assignment.category.adapter.output.persistence.jpa.repository.CategoryEntityRepository
import com.musinsa.assignment.category.application.port.output.CategoryOutputPort
import com.musinsa.assignment.category.domain.Category
import com.musinsa.assignment.category.domain.CategoryId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CategoryPersistenceAdapter(
    private val categoryEntityRepository: CategoryEntityRepository,
) : CategoryOutputPort {
    override fun save(category: Category): Category {
        val saved = categoryEntityRepository.save(category.toEntity())
        return saved.toDomain()
    }

    override fun delete(id: CategoryId) {
        categoryEntityRepository.deleteById(id.value)
    }

    override fun findById(id: CategoryId): Category? = categoryEntityRepository.findByIdOrNull(id.value)?.let { it.toDomain() }

    override fun findAll(): List<Category> = categoryEntityRepository.findAll().map { it.toDomain() }

    override fun findAll(pageable: Pageable): Page<Category> = categoryEntityRepository.findAll(pageable).map { it.toDomain() }

    override fun findByName(name: String): Category? = categoryEntityRepository.findByName(name)?.let { it.toDomain() }
}
