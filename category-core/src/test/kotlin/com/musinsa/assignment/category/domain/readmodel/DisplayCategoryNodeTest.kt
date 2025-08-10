package com.musinsa.assignment.category.domain.readmodel

import com.musinsa.assignment.category.domain.CategoryFixture
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryNodeFixture.displayCategoryNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("CategoryTree 도메인 테스트")
class DisplayCategoryNodeTest {
    @Test
    fun `findById_ID로 노드를 찾을 수 있다`() {
        // given
        val root = samples()
        val targetId = CategoryId(2L)

        // when
        val actual = root.findById(targetId)

        // then
        assertThat(actual?.category)
            .isNotNull
            .extracting("id", "name")
            .containsExactly(CategoryId(2L), "하의")
    }

    @Test
    fun `findById_존재하지 않는 ID로 검색하면_null을 반환한다`() {
        // given
        val root = samples()
        val nonExistentId = CategoryId(999L)

        // when
        val found = root.findById(nonExistentId)

        // then
        assertThat(found).isNull()
    }

    @Test
    fun `isLeaf_자식이 없는 노드는_True를 리턴한다`() {
        // given
        val leafNode = displayCategoryNode(category = CategoryFixture.category())

        // when
        val actual = leafNode.isLeaf()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `isLeaf_자식이 있는 노드는_False를 리턴한다`() {
        // given
        val nonLeafNode =
            displayCategoryNode(
                category = CategoryFixture.category(),
                children =
                    mutableListOf(
                        displayCategoryNode(category = CategoryFixture.category()),
                    ),
            )
        // when
        val actual = nonLeafNode.isLeaf()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `hasChild_특정 ID를 가진 자식 노드가 있으면_True를 반환한다`() {
        // given
        val root = samples()

        // when
        val actual = root.hasChild(CategoryId(2L))

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `hasChild_특정 ID를 가진 자식 노드가 없으면_False를 반환한다`() {
        // given
        val root = samples()

        // when
        val actual = root.hasChild(CategoryId(999L))

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `size_예제 샘플 노드의 개수는_4개다`() {
        // given
        val root = samples()

        // when
        val actual = root.size()

        // then
        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun `directChildrenCount_예제 샘플의 직계 자식 노드의 개수는_2개이다`() {
        // given
        val root = samples()

        // when
        val directChildCount = root.directChildrenCount()

        // then
        assertThat(directChildCount).isEqualTo(2) // 하의, 상의
    }

    @Test
    fun `sortedChildren_자식 노드를 정렬하여_반환한다`() {
        // given
        val root =
            displayCategoryNode(
                category = CategoryFixture.category(id = 1L, name = "의류"),
                children =
                    mutableListOf(
                        displayCategoryNode(category = CategoryFixture.category(id = 2L, name = "하의"), order = 2),
                        displayCategoryNode(category = CategoryFixture.category(id = 3L, name = "상의"), order = 1),
                        displayCategoryNode(category = CategoryFixture.category(id = 4L, name = "신발"), order = 3),
                        displayCategoryNode(category = CategoryFixture.category(id = 5L, name = "기획전"), order = 0),
                    ),
            )

        // when
        val sortedChildren = root.sortedChildren()

        // then
        assertThat(sortedChildren.map { it.category.name }).containsExactly(
            "기획전",
            "상의",
            "하의",
            "신발",
        )
    }

    private fun samples(): DisplayCategoryNode {
        val rootCategory = CategoryFixture.category(id = 1L, name = "의류")
        val pantsCategory = CategoryFixture.category(id = 2L, name = "하의")
        val jeansCategory = CategoryFixture.category(id = 3L, name = "청바지")
        val topCategory = CategoryFixture.category(id = 4L, name = "상의")
        return displayCategoryNode(
            category = rootCategory,
            depth = 0,
            order = 0,
            children =
                mutableListOf(
                    displayCategoryNode(
                        category = pantsCategory,
                        depth = 1,
                        order = 0,
                        children =
                            mutableListOf(
                                displayCategoryNode(
                                    category = jeansCategory,
                                    depth = 2,
                                    order = 0,
                                ),
                            ),
                    ),
                    displayCategoryNode(category = topCategory, depth = 1, order = 1),
                ),
        )
    }
}
