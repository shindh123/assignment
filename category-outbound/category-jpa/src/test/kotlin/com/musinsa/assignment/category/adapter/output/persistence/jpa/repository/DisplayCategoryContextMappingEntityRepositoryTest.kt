package com.musinsa.assignment.category.adapter.output.persistence.jpa.repository

import com.musinsa.assignment.category.adapter.output.persistence.jpa.TestcontainerConfiguration
import com.musinsa.assignment.shared.adapter.output.persistence.jpa.config.JpaConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.junit.jupiter.Testcontainers

@ActiveProfiles("test")
@ContextConfiguration(classes = [JpaConfiguration::class, TestcontainerConfiguration::class])
@DirtiesContext
@DataJpaTest
@Testcontainers
internal class DisplayCategoryContextMappingEntityRepositoryTest {
    @Autowired private lateinit var repository: DisplayCategoryContextMappingEntityRepository

    @Test
    @Sql("/sqls/category_mapping.sql")
    fun `findSubTree_루트ID로 조회하면_전체 계층구조를 반환한다`() {
        // given
        val rootId = 1L
        val displayId = 1L

        // when
        val actual = repository.findSubTree(rootId, displayId)

        // then
        assertThat(actual).hasSize(4)
        assertThat(actual.map { it.depth }).containsExactly(0, 1, 1, 2)
        assertThat(actual.map { it.categoryName }).containsExactly("의류", "상의", "하의", "티셔츠")
    }

    @Test
    @Sql("/sqls/category_mapping.sql")
    fun `findSubTree_중간 노드ID로 조회하면_해당 노드의 하위구조만 반환한다`() {
        // given
        val topsId = 2L
        val displayId = 1L

        // when
        val actual = repository.findSubTree(topsId, displayId)

        // then
        assertThat(actual).hasSize(2)
        assertThat(actual.map { it.depth }).containsExactly(0, 1)
        assertThat(actual.map { it.categoryName }).containsExactly("상의", "티셔츠")
    }

    @Test
    @Sql("/sqls/category_mapping.sql")
    fun `findSubTree_리프노드ID로 조회하면_해당 노드만 반환한다`() {
        // given
        val tShirtId = 4L
        val displayId = 1L

        // when
        val actual = repository.findSubTree(tShirtId, displayId)

        // then
        assertThat(actual).hasSize(1)
        assertThat(actual.map { it.depth }).containsExactly(0)
        assertThat(actual.map { it.categoryName }).containsExactly("티셔츠")
    }
}
