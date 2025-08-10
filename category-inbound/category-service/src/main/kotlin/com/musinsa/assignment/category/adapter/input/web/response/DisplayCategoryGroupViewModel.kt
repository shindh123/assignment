package com.musinsa.assignment.category.adapter.input.web.response

data class DisplayCategoryGroupViewModel(
    val displayId: Long,
    val displayName: String,
    val root: DisplayCategoryNodeViewModel,
) {
    data class DisplayCategoryNodeViewModel(
        val categoryId: Long,
        val depth: Int,
        val order: Int,
        val categoryName: String,
        val children: List<DisplayCategoryNodeViewModel> = emptyList(),
    )
}
