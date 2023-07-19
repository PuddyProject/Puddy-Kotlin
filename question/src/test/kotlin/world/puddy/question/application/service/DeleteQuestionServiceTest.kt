package world.puddy.question.application.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import world.puddy.common.error.exception.QuestionNotFoundException
import world.puddy.question.application.port.out.DeleteQuestionPort

class DeleteQuestionServiceTest : BehaviorSpec({
    val deleteQuestionPort = mockk<DeleteQuestionPort>()
    val deleteQuestionService = DeleteQuestionService(deleteQuestionPort)

    given("질문글 삭제하려고 할 때") {
        val targetId = 1L
        val notRegisterId = 2L
        `when`("존재하는 id로 삭제를 요청할 때") {
            every { deleteQuestionPort.deleteQuestion(targetId) } answers { Unit }
            deleteQuestionService.deleteQuestion(targetId)
            then("삭제가 성공해야 한다.") {
                verify(exactly = 1) { deleteQuestionPort.deleteQuestion(targetId) }
            }
        }
        `when`("존재하지 않는 id로 삭제를 요청할 때") {
            every { deleteQuestionPort.deleteQuestion(notRegisterId) } answers { throw QuestionNotFoundException() }
            then("삭제가 실패해야 한다.") {
                shouldThrow<QuestionNotFoundException> {
                    deleteQuestionService.deleteQuestion(notRegisterId)
                }
            }
        }
    }
})
