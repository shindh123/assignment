package com.musinsa.assignment.category.adapter.output.persistence.jpa.repository

import com.musinsa.assignment.category.adapter.output.persistence.jpa.entity.DisplayEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DisplayEntityRepository : JpaRepository<DisplayEntity, Long> {
    fun findByName(name: String): DisplayEntity?
}
