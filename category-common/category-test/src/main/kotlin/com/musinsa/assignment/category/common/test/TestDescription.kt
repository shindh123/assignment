package com.musinsa.assignment.com.musinsa.assignment.category.common.test

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class TestDescription(
    val value: String = "",
)
