package com.musinsa.assignment.category.adapter.output.persistence.jpa.repository

import com.musinsa.assignment.category.adapter.output.persistence.jpa.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface CategoryEntityRepository : JpaRepository<CategoryEntity, Long> {
    /**
     * 멱등하게 처리하기 위해서는 아래 쿼리로 save 한다
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT IGNORE INTO categories (name) VALUES (:name)", nativeQuery = true)
    fun saveOrIgnoreIfExists(name: String): Int

    fun findByName(name: String): CategoryEntity?
}
