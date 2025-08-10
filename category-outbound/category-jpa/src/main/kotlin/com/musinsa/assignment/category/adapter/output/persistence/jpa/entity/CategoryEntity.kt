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
    name = "categories",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_name",
            columnNames = ["name"],
        ),
    ],
)
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0L,
    @Column(nullable = false)
    val name: String,
)
