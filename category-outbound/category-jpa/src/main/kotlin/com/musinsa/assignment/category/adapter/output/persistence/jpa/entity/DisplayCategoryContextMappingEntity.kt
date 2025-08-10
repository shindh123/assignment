package com.musinsa.assignment.category.adapter.output.persistence.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "display_category_context_mappings",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_display_category_context_mappings",
            columnNames = ["display_id", "category_id"],
        ),
    ],
)
class DisplayCategoryContextMappingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "display_id", nullable = false)
    val displayId: Long,
    @Column(name = "category_id", nullable = false)
    val categoryId: Long,
    @Column(name = "parent_id")
    val parentId: Long? = null,
    @Column(name = "ordering", nullable = false)
    val ordering: Int,
)
