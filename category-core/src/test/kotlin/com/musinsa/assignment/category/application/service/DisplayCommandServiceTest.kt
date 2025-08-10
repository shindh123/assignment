package com.musinsa.assignment.category.application.service

import com.musinsa.assignment.category.application.port.input.DeleteDisplayUseCase.DeleteDisplayCommand
import com.musinsa.assignment.category.application.port.output.DisplayCategoryContextOutputPort
import com.musinsa.assignment.category.application.port.output.DisplayOutputPort
import com.musinsa.assignment.category.domain.DisplayId
import com.musinsa.assignment.category.domain.exceptions.ResourceInUseException
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DisplayCommandServiceTest {
    private var displayOutputPort: DisplayOutputPort = mockk()
    private var displayCategoryContextOutputPort: DisplayCategoryContextOutputPort = mockk()
    private var displayCommandService: DisplayCommandService = DisplayCommandService(displayOutputPort, displayCategoryContextOutputPort)

    @Nested
    @DisplayName("delete 기능 테스트")
    inner class DeleteMethod {
        @Test
        fun `전시가 카테고리 매핑이 되어 있지 않으면_삭제가 된다`() {
            // given
            val displayId = DisplayId(1L)
            val command = DeleteDisplayCommand(displayId)
            every { displayCategoryContextOutputPort.findByDisplayId(displayId) } returns emptyList()
            every { displayOutputPort.delete(displayId) } just runs

            // when
            displayCommandService.delete(command)

            // then
            verify { displayCategoryContextOutputPort.findByDisplayId(displayId) }
            verify { displayOutputPort.delete(displayId) }
        }

        @Test
        fun `전시가 카테고리 매핑이 되어 있으면_ResourceInUseException이 발생한다`() {
            // given
            val displayId = DisplayId(1L)
            val command = DeleteDisplayCommand(displayId)
            every { displayCategoryContextOutputPort.findByDisplayId(displayId) } returns listOf(mockk())

            // when & then
            assertThatThrownBy { displayCommandService.delete(command) }
                .isInstanceOf(ResourceInUseException::class.java)

            verify { displayCategoryContextOutputPort.findByDisplayId(displayId) }
            verify(exactly = 0) { displayOutputPort.delete(any()) }
        }
    }
}
