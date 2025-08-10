package com.musinsa.assignment.category.domain.extensions

internal fun <T> MutableSet<T>.containsNot(element: T): Boolean = !this.contains(element)

internal fun <T> MutableList<T>.containsNot(element: T): Boolean = !this.contains(element)
