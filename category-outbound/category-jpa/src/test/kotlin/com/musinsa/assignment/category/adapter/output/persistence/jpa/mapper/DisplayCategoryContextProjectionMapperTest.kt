package com.musinsa.assignment.category.adapter.output.persistence.jpa.mapper

import com.musinsa.assignment.category.adapter.output.persistence.jpa.projection.DisplayCategoryContextProjection
import com.musinsa.assignment.com.musinsa.assignment.category.common.test.TestDescription
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DisplayCategoryContextProjectionMapperTest {
    @Test
    fun `buildReadModel_루트 계층구조 데이터가 주어지면_트리형태의 ReadModel을 리턴한다`() {
        // given
        val projections =
            listOf(
                DisplayCategoryContextProjectionImpl(
                    mappingId = 1L,
                    categoryId = 1L,
                    displayId = 1L,
                    categoryName = "의류",
                    parentId = null,
                    depth = 0,
                    ordering = 1,
                ),
                DisplayCategoryContextProjectionImpl(
                    mappingId = 2L,
                    categoryId = 2L,
                    displayId = 1L,
                    categoryName = "상의",
                    parentId = 1L,
                    depth = 1,
                    ordering = 1,
                ),
                DisplayCategoryContextProjectionImpl(
                    mappingId = 3L,
                    categoryId = 3L,
                    displayId = 1L,
                    categoryName = "하의",
                    parentId = 1L,
                    depth = 1,
                    ordering = 2,
                ),
                DisplayCategoryContextProjectionImpl(
                    mappingId = 4L,
                    categoryId = 4L,
                    displayId = 1L,
                    categoryName = "티셔츠",
                    parentId = 2L,
                    depth = 2,
                    ordering = 1,
                ),
            )

        // when
        val actual = projections.buildReadModel()

        // then
        assertThat(actual.category.id?.value).isEqualTo(1L)
        assertThat(actual.category.name).isEqualTo("의류")
        assertThat(actual.depth).isEqualTo(0)
        assertThat(actual.order).isEqualTo(1)

        // 의류 하위 카테고리 검증
        assertThat(actual.children).hasSize(2)
        val tops = actual.children[0]
        val bottoms = actual.children[1]

        assertThat(tops.category.name).isEqualTo("상의")
        assertThat(tops.depth).isEqualTo(1)
        assertThat(tops.order).isEqualTo(1)

        assertThat(bottoms.category.name).isEqualTo("하의")
        assertThat(bottoms.depth).isEqualTo(1)
        assertThat(bottoms.order).isEqualTo(2)

        // 상의 하위 카테고리 검증
        assertThat(tops.children).hasSize(1)
        val tshirt = tops.children[0]
        assertThat(tshirt.category.name).isEqualTo("티셔츠")
        assertThat(tshirt.depth).isEqualTo(2)
        assertThat(tshirt.order).isEqualTo(1)
    }

    @Test
    @TestDescription("ParentId가 null이 아닌 케이스 (중간 계층일 경우) 테스트")
    fun `buildReadModel_중간계층구조 데이터가 주어지면_트리형태의 ReadModel을 리턴한다`() {
        // given
        val projections =
            listOf(
                DisplayCategoryContextProjectionImpl(
                    mappingId = 2L,
                    categoryId = 2L,
                    displayId = 1L,
                    categoryName = "의류",
                    parentId = 1L,
                    depth = 1,
                    ordering = 1,
                ),
                DisplayCategoryContextProjectionImpl(
                    mappingId = 3L,
                    categoryId = 3L,
                    displayId = 1L,
                    categoryName = "상의",
                    parentId = 2L,
                    depth = 2,
                    ordering = 1,
                ),
                DisplayCategoryContextProjectionImpl(
                    mappingId = 4L,
                    categoryId = 4L,
                    displayId = 1L,
                    categoryName = "하의",
                    parentId = 2L,
                    depth = 2,
                    ordering = 2,
                ),
                DisplayCategoryContextProjectionImpl(
                    mappingId = 5L,
                    categoryId = 5L,
                    displayId = 1L,
                    categoryName = "티셔츠",
                    parentId = 3L,
                    depth = 3,
                    ordering = 1,
                ),
            )

        // when
        val actual = projections.buildReadModel()

        // then
        assertThat(actual.category.id?.value).isEqualTo(2L)
        assertThat(actual.category.name).isEqualTo("의류")
        assertThat(actual.depth).isEqualTo(1)
        assertThat(actual.order).isEqualTo(1)

        // 의류 하위 카테고리 검증
        assertThat(actual.children).hasSize(2)
        val tops = actual.children[0]
        val bottoms = actual.children[1]

        assertThat(tops.category.name).isEqualTo("상의")
        assertThat(tops.depth).isEqualTo(2)
        assertThat(tops.order).isEqualTo(1)

        assertThat(bottoms.category.name).isEqualTo("하의")
        assertThat(bottoms.depth).isEqualTo(2)
        assertThat(bottoms.order).isEqualTo(2)

        // 상의 하위 카테고리 검증
        assertThat(tops.children).hasSize(1)
        val tshirt = tops.children[0]
        assertThat(tshirt.category.name).isEqualTo("티셔츠")
        assertThat(tshirt.depth).isEqualTo(3)
        assertThat(tshirt.order).isEqualTo(1)
    }

    @Test
    fun `buildReadModel_단일_카테고리가_주어지면_자식이_없는_ReadModel을_반환한다`() {
        // given
        val projections =
            listOf(
                DisplayCategoryContextProjectionImpl(
                    mappingId = 1L,
                    categoryId = 1L,
                    displayId = 1L,
                    categoryName = "의류",
                    parentId = null,
                    depth = 0,
                    ordering = 1,
                ),
            )

        // when
        val actual = projections.buildReadModel()

        // then
        assertThat(actual.category.id?.value).isEqualTo(1L)
        assertThat(actual.category.name).isEqualTo("의류")
        assertThat(actual.depth).isEqualTo(0)
        assertThat(actual.order).isEqualTo(1)
        assertThat(actual.children).isEmpty()
    }

    data class DisplayCategoryContextProjectionImpl(
        override val mappingId: Long,
        override val displayId: Long,
        override val categoryId: Long,
        override val parentId: Long?,
        override val depth: Int,
        override val ordering: Int,
        override val categoryName: String,
    ) : DisplayCategoryContextProjection
}
