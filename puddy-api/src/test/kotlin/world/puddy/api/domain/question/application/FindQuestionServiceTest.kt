package world.puddy.api.domain.question.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import world.puddy.api.domain.question.QuestionFixture
import world.puddy.core.domain.question.application.port.out.FindQuestionPort
import world.puddy.core.domain.question.application.service.FindQuestionService
import world.puddy.core.global.error.exception.QuestionNotFoundException

class FindQuestionServiceTest : BehaviorSpec({
    val findQuestionPort = mockk<FindQuestionPort>()
    val sut = FindQuestionService(findQuestionPort)

    Given("질문글을 조회하는 경우") {
        When("존재하는 id일 때") {
            val existedId = 1L
            val question = QuestionFixture.question()
            every { findQuestionPort.findQuestion(any()) } returns question
            Then("질문글이 조회된다.") {
                sut.findQuestion(existedId)

                verify(exactly = 1) {
                    findQuestionPort.findQuestion(any())
                }
            }
        }
        When("존재하지 않는 id일 때") {
            val notExistedId = 2L
            every { findQuestionPort.findQuestion(any()) } throws QuestionNotFoundException()
            Then("예외가 발생한다.") {
                shouldThrow<QuestionNotFoundException> {
                    sut.findQuestion(notExistedId)
                }
            }
        }
    }
})
