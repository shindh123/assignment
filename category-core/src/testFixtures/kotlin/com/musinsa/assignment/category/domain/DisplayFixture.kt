package com.musinsa.assignment.category.domain

import com.appmattus.kotlinfixture.kotlinFixture

object DisplayFixture {
    val fixture = kotlinFixture()

    fun display(
        id: Long? = null,
        name: String? = null,
        description: String? = null,
    ) = fixture<Display> {
        id?.let { property(Display::id) { DisplayId(id) } }
        name?.let { property(Display::name) { it } }
        description?.let { property(Display::description) { it } }
    }

    fun displayId() = fixture<DisplayId>()
}
