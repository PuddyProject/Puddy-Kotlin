package world.puddy.question.application.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import world.puddy.common.error.ErrorCode
import world.puddy.common.error.exception.QuestionNotFoundException
import world.puddy.question.QuestionSnippets
import world.puddy.question.application.port.out.FindQuestionPort

class FindQuestionServiceTest : BehaviorSpec({
    val findQuestionPort = mockk<FindQuestionPort>()
    val findQuestionService = FindQuestionService(findQuestionPort)
    val mockMvc = MockMvcBuilders.standaloneSetup(findQuestionService).build()

    given("질문 조회 서비스에서") {
        val questionId = 1L
        val response = QuestionSnippets.findQuestionResponse()
        every { findQuestionPort.findQuestion(questionId) } answers { QuestionSnippets.question() }
        `when`("올바른 id로 조회했을 때") {
            val actual = findQuestionService.findQuestion(questionId)
            then("해당 id에 대한 질문글을 반환한다.") {
                actual.id shouldBe response.id
                actual.title shouldBe response.title
                actual.content shouldBe response.content
                actual.category shouldBe response.category
                actual.postCategory shouldBe response.postCategory
            }
        }
        `when`("없는 id일 경우") {
            val notRegisterId = 2L
            every { findQuestionPort.findQuestion(notRegisterId) } answers { throw QuestionNotFoundException() }
            then("QuestionNotFoundException이 발생한다.") {
                shouldThrow<QuestionNotFoundException> {
                    findQuestionService.findQuestion(notRegisterId)
                }.message shouldBe ErrorCode.QUESTION_NOT_FOUND.message
                verify(exactly = 1) { findQuestionPort.findQuestion(notRegisterId) }
            }
        }
    }
})
