package com.musinsa.assignment.category.domain

import com.appmattus.kotlinfixture.kotlinFixture

object CategoryFixture {
    private val fixture = kotlinFixture()

    fun category(
        id: Long? = null,
        name: String? = null,
    ) = fixture<Category> {
        id?.let { property(Category::id) { CategoryId(it) } }
        name?.let { property(Category::name) { it } }
    }

    fun categoryId() = fixture<CategoryId>()
}
