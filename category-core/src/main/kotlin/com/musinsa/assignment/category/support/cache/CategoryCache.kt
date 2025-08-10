package com.musinsa.assignment.category.support.cache

import com.musinsa.assignment.category.support.cache.CacheNames.CATEGORY_TREE_NAME
import java.time.Duration

enum class CategoryCache(
    val cacheName: String,
    val ttl: Duration,
) {
    CATEGORY_TREE(
        cacheName = CATEGORY_TREE_NAME,
        ttl = Duration.ofMinutes(10),
    ),
}

object CacheNames {
    const val CATEGORY_TREE_NAME: String = "category-tree"
}
