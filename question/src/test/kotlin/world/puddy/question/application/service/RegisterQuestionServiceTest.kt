package world.puddy.question.application.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import world.puddy.question.QuestionSnippets
import world.puddy.question.application.port.out.RegisterQuestionPort

class RegisterQuestionServiceTest : BehaviorSpec({

    val registerQuestionPort = mockk<RegisterQuestionPort>()
    val registerQuestionService = RegisterQuestionService(registerQuestionPort)

    given("질문글을 등록할 때") {
        val expectedId = 1L
        val request = QuestionSnippets.registerQuestionCommand
        `when`("필수 정보를 모두 입력하면") {
            every { registerQuestionPort.registerQuestion(request) } answers { expectedId }
            then("등록이 성공해야 한다.") {
                val actual = registerQuestionService.registerQuestion(request)
                actual shouldBe expectedId

                verify(exactly = 1) { registerQuestionPort.registerQuestion(request) }
            }
        }
    }
})
