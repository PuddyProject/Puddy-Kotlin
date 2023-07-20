package world.puddy.question.application.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import world.puddy.common.error.exception.QuestionNotFoundException
import world.puddy.question.QuestionSnippets
import world.puddy.question.application.port.out.EditQuestionPort

class EditQuestionServiceTest : BehaviorSpec({
    val editQuestionPort = mockk<EditQuestionPort>()
    val editQuestionService = EditQuestionService(editQuestionPort)

    given("등록된 질문글을 수정할 때") {
        val registeredId = 1L
        val request = QuestionSnippets.editQuestionCommand
        `when`("필수 정보를 모두 입력하면") {
            every { editQuestionPort.editQuestion(registeredId, request) } answers { registeredId }
            then("수정이 성공해야 한다.") {
                val actual = editQuestionService.editQuestion(registeredId, request)
                actual shouldBe registeredId
                verify(exactly = 1) { editQuestionPort.editQuestion(registeredId, request) }
            }
        }
        `when`("없는 id를 수정할 경우") {
            val notRegisterId = 2L
            every {
                editQuestionPort.editQuestion(
                    notRegisterId,
                    request
                )
            } answers { throw QuestionNotFoundException() }
            then("QuestionNotFoundException이 발생한다.") {
                shouldThrow<QuestionNotFoundException> {
                    editQuestionService.editQuestion(notRegisterId, request)
                }
                verify(exactly = 1) { editQuestionPort.editQuestion(notRegisterId, request) }
            }
        }
    }
})
