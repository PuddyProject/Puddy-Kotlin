package world.puddy.api.domain.question.application

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import world.puddy.api.domain.question.QuestionFixture
import world.puddy.core.domain.image.application.port.`in`.SaveImageUseCase
import world.puddy.core.domain.question.application.port.out.RegisterQuestionPort
import world.puddy.core.domain.question.application.service.RegisterQuestionService

class QuestionServiceTest : BehaviorSpec({
    val registerQuestionPort = mockk<RegisterQuestionPort>()
    val saveImageUseCase = mockk<SaveImageUseCase>()
    val questionService = RegisterQuestionService(registerQuestionPort,saveImageUseCase)

    given("질문글을 등록할 때") {
        val command = QuestionFixture.registerQuestionCommand()
        `when`("모든 정보가 포함되면") {
            every { registerQuestionPort.registerQuestion(any()) } returns 1L
            then("질문글이 등록된다") {
                questionService.registerQuestion(command)
                verify(exactly = 1) { registerQuestionPort.registerQuestion(command) }
            }
            and("이미지가 포함되지 않으면 이미지 저장 함수가 실행되지 않는다.") {
                verify(exactly = 0) { saveImageUseCase.saveImage(any()) }
            }
        }
    }


})