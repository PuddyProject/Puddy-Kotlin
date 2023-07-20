package world.puddy.question.adapter.`in`.web

import io.kotest.core.spec.style.DescribeSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import world.puddy.question.application.port.`in`.DeleteQuestionUseCase

class DeleteQuestionControllerTest : DescribeSpec({
    val deleteQuestionUseCase = mockk<DeleteQuestionUseCase>()
    val deleteQuestionController = DeleteQuestionController(deleteQuestionUseCase)
    val mockMvc = MockMvcBuilders.standaloneSetup(deleteQuestionController).build()

    afterContainer {
        clearAllMocks()
    }

    describe("질문 삭제 요청을 할 때") {
        val existId = 1L
        context("삭제할 질문이 존재하면") {
            every { deleteQuestionUseCase.deleteQuestion(existId) } returns Unit
            it("삭제가 성공해야 한다.") {
                mockMvc.delete("/questions/$existId")
                    .andExpect { status { isOk() } }
                    .andDo { print() }
            }
        }
    }
})
