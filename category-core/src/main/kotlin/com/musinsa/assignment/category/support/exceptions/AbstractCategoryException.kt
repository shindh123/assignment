package com.musinsa.assignment.category.support.exceptions

import java.net.URI

abstract class AbstractCategoryException(
    val status: Int,
    val reason: String? = null,
    cause: Throwable? = null,
) : RuntimeException(reason, cause) {
    override val message: String
        get() =
            buildString {
                append(status)
                reason?.let { append("[").append(it).append("]") }
                cause?.let { append('\n').append("[exception : ").append(it).append("]") }
            }

    fun type(): URI = URI.create("$SCHEME:${this.javaClass.simpleName}")

    companion object {
        const val SCHEME: String = "exception"
    }
}
