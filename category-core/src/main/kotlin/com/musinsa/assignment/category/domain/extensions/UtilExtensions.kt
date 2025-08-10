package com.musinsa.assignment.category.domain.extensions

inline fun <T> T?.ifPresentOrElse(
    onPresent: (T) -> Unit,
    onAbsent: () -> Unit,
) {
    if (this != null) onPresent(this) else onAbsent()
}
