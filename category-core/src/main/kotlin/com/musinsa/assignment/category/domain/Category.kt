package com.musinsa.assignment.category.domain

data class Category(
    val id: CategoryId? = null,
    val name: String,
) {
    fun rename(newName: String): Category = this.copy(name = newName)
}
