package com.musinsa.assignment.category.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Category 도메인 테스트")
class CategoryTest {
    @Test
    fun `rename_이름을 변경할 경우_새로운 Category가 생성된다`() {
        // given
        val category = CategoryFixture.category(id = 1L)
        val newName = "새로운 이름"

        // when
        val renamed = category.rename(newName)

        // then
        assertThat(renamed)
            .isNotSameAs(category)
            .extracting("id", "name")
            .containsExactly(category.id, newName)
    }

    @Test
    fun `rename_이름을 변경하더라도_ID는 유지된다`() {
        // given
        val categoryId = CategoryId(1L)
        val category = Category(id = categoryId, name = "원래이름")

        // when
        val renamed = category.rename("새이름")

        // then
        assertThat(renamed.id).isEqualTo(categoryId)
    }
}
