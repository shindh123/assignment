package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.DeleteCategoryUseCase.DeleteCategoryCommand
import com.musinsa.assignment.category.application.port.output.CategoryOutputPort
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.domain.CategoryId
import com.musinsa.assignment.category.domain.DisplayCategoryContext
import com.musinsa.assignment.category.domain.exceptions.ResourceInUseException
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CategoryCommandServiceTest {
    private var categoryOutputPort: CategoryOutputPort = mockk()
    private var displayCategoryContextOutputPort: DisplayCategoryContextOutputPort = mockk()
    private var categoryCommandService: CategoryCommandService =
        CategoryCommandService(
            categoryOutputPort = categoryOutputPort,
            displayCategoryContextOutputPort = displayCategoryContextOutputPort,
        )

    @Nested
    @DisplayName("delete 기능 테스트")
    inner class DeleteMethod {
        @Test
        fun `카테고리가 전시 매핑이 되어 있지 않으면_삭제가 된다`() {
            // given
            val categoryId = CategoryId(1L)
            val command = DeleteCategoryCommand(categoryId)
            every { displayCategoryContextOutputPort.findByCategoryId(categoryId) } returns emptyList()
            every { categoryOutputPort.delete(categoryId) } just runs

            // when
            categoryCommandService.delete(command)

            // then
            verify { displayCategoryContextOutputPort.findByCategoryId(categoryId) }
            verify { categoryOutputPort.delete(categoryId) }
        }

        @Test
        fun `카테고리가 전시 매핑이 사용중 일 경우_ResourceInUseException 에러가 발생한다`() {
            // given
            val categoryId = CategoryId(1L)
            val command = DeleteCategoryCommand(categoryId)
            val mockMappings = listOf(mockk<DisplayCategoryContext>())
            every { displayCategoryContextOutputPort.findByCategoryId(categoryId) } returns mockMappings

            // when & then
            assertThatThrownBy { categoryCommandService.delete(command) }
                .isInstanceOf(ResourceInUseException::class.java)
        }
    }
}
