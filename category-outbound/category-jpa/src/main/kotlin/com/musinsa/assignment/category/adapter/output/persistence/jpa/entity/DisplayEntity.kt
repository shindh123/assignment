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
    name = "displays",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_name",
            columnNames = ["name"],
        ),
    ],
)
class DisplayEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0L,
    @Column(nullable = false)
    val name: String,
    @Column
    val description: String?,
)
