package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.AssignCategoryToDisplayUseCase
import com.musinsa.assignment.category.application.port.input.UpdateDisplayCategoryContextUseCase.UpdateDisplayCategoryContextCommand
import com.musinsa.assignment.category.application.port.output.CategoryOutputPort
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.domain.*
import com.musinsa.assignment.category.domain.exceptions.AlreadyAssignedRootCategoryException
import com.musinsa.assignment.category.domain.exceptions.CategoryNotFoundException
import com.musinsa.assignment.category.domain.exceptions.DisplayCategoryContextNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class DisplayCategoryContextCommandServiceTest {
    private var categoryOutputPort: CategoryOutputPort = mockk()
    private var displayCategoryContextOutputPort: DisplayCategoryContextOutputPort = mockk()

    private var service: DisplayCategoryContextCommandService =
        DisplayCategoryContextCommandService(
            categoryOutputPort = categoryOutputPort,
            displayCategoryContextOutputPort = displayCategoryContextOutputPort,
        )

    @Nested
    @DisplayName("assignCategoryToDisplay 기능 테스트")
    inner class AssignCategoryToDisplayTest {
        @Test
        fun `parentId가 없는 경우_정상적으로 할당한다`() {
            // given
            val displayId = DisplayId(1L)
            val categoryId = CategoryId(100L)
            val order = 1
            val command =
                AssignCategoryToDisplayUseCase.AssignCategoryToDisplayCommand(
                    displayId = displayId,
                    categoryId = categoryId,
                    parentId = null,
                    order = order,
                )

            val expectedContext =
                DisplayCategoryContext(
                    categoryId = categoryId,
                    displayId = displayId,
                    parentId = null,
                    order = order,
                )

            every { displayCategoryContextOutputPort.findRootCategoryByDisplayId(displayId) } returns null
            every { displayCategoryContextOutputPort.save(any()) } returns expectedContext

            // when
            val actual = service.assignCategoryToDisplay(command)

            // then
            assertThat(actual).isEqualTo(expectedContext)
            verify { displayCategoryContextOutputPort.findRootCategoryByDisplayId(displayId) }
            verify { displayCategoryContextOutputPort.save(any()) }
            verify(exactly = 0) { categoryOutputPort.findById(any()) }
        }

        @Test
        fun `이미 루트 카테고리가 할당된 경우_AlreadyAssignedRootCategoryException 가 발생한다`() {
            // given
            val displayId = DisplayId(1L)
            val categoryId = CategoryId(100L)
            val command =
                AssignCategoryToDisplayUseCase.AssignCategoryToDisplayCommand(
                    displayId = displayId,
                    categoryId = categoryId,
                    parentId = null,
                    order = 1,
                )

            val existingRootContext =
                DisplayCategoryContext(
                    categoryId = CategoryId(99L),
                    displayId = displayId,
                    parentId = null,
                    order = 0,
                )

            every { displayCategoryContextOutputPort.findRootCategoryByDisplayId(displayId) } returns existingRootContext

            // when & then
            assertThatThrownBy { service.assignCategoryToDisplay(command) }
                .isInstanceOf(AlreadyAssignedRootCategoryException::class.java)

            verify { displayCategoryContextOutputPort.findRootCategoryByDisplayId(displayId) }
            verify(exactly = 0) { displayCategoryContextOutputPort.save(any()) }
        }

        @Test
        fun `parentId가 있고 부모 카테고리가 존재하는 경우_정상적으로 할당한다`() {
            // given
            val displayId = DisplayId(1L)
            val categoryId = CategoryId(100L)
            val parentId = CategoryId(50L)
            val order = 2
            val command =
                AssignCategoryToDisplayUseCase.AssignCategoryToDisplayCommand(
                    displayId = displayId,
                    categoryId = categoryId,
                    parentId = parentId,
                    order = order,
                )

            val parentCategory = Category(id = parentId, name = "부모 카테고리")
            val expectedContext =
                DisplayCategoryContext(
                    categoryId = categoryId,
                    displayId = displayId,
                    parentId = parentId,
                    order = order,
                )

            every { categoryOutputPort.findById(parentId) } returns parentCategory
            every { displayCategoryContextOutputPort.save(any()) } returns expectedContext

            // when
            val actual = service.assignCategoryToDisplay(command)

            // then
            assertThat(actual).isEqualTo(expectedContext)
            verify { categoryOutputPort.findById(parentId) }
            verify { displayCategoryContextOutputPort.save(any()) }
            verify(exactly = 0) { displayCategoryContextOutputPort.findRootCategoryByDisplayId(any()) }
        }

        @Test
        fun `parentId가 있지만 부모 카테고리가 존재하지 않는 경우_CategoryNotFoundException 예외가 발생한다`() {
            // given
            val displayId = DisplayId(1L)
            val categoryId = CategoryId(100L)
            val parentId = CategoryId(999L) // 존재하지 않는 부모 ID
            val command =
                AssignCategoryToDisplayUseCase.AssignCategoryToDisplayCommand(
                    displayId = displayId,
                    categoryId = categoryId,
                    parentId = parentId,
                    order = 1,
                )

            every { categoryOutputPort.findById(parentId) } returns null

            // when & then
            assertThatThrownBy { service.assignCategoryToDisplay(command) }
                .isInstanceOf(CategoryNotFoundException::class.java)

            verify { categoryOutputPort.findById(parentId) }
            verify(exactly = 0) { displayCategoryContextOutputPort.save(any()) }
        }
    }

    @Test
    fun `update_존재하지 않는 ID일 경우_DisplayCategoryContextNotFoundException 이 발생한다`() {
        // given
        val nonExistingId = DisplayCategoryContextId(999L)
        every { displayCategoryContextOutputPort.findById(nonExistingId) } returns null

        val command =
            UpdateDisplayCategoryContextCommand(
                id = nonExistingId,
                displayId = DisplayId(100L),
                categoryId = CategoryId(10L),
                parentId = CategoryId(5L),
                order = 1,
            )

        // when then
        assertThatThrownBy { service.update(command) }
            .isInstanceOf(DisplayCategoryContextNotFoundException::class.java)
    }
}
