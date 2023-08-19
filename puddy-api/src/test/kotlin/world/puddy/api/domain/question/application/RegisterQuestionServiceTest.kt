package world.puddy.api.domain.question.application

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import world.puddy.api.domain.question.QuestionFixture
import world.puddy.core.domain.image.application.port.`in`.SaveImageUseCase
import world.puddy.core.domain.question.application.port.out.RegisterQuestionPort
import world.puddy.core.domain.question.application.service.RegisterQuestionService

class RegisterQuestionServiceTest : BehaviorSpec({
    val registerQuestionPort = mockk<RegisterQuestionPort>()
    val saveImageUseCase = mockk<SaveImageUseCase>()
    val sut = RegisterQuestionService(registerQuestionPort, saveImageUseCase)

    Given("질문글을 등록하는 경우") {
        When("올바른 요청 폼일 때") {
            val question = QuestionFixture.question()
            val images = QuestionFixture.images()
            val command = QuestionFixture.registerQuestionCommand()

            every { registerQuestionPort.registerQuestion(any()) } returns question
            every { saveImageUseCase.saveImage(any()) } returns images
            Then("질문글이 생성된다.") {
                sut.registerQuestion(command)

                verifySequence {
                    registerQuestionPort.registerQuestion(any())
                    saveImageUseCase.saveImage(any())
                    registerQuestionPort.registerQuestion(any())
                }
            }
        }
    }
})
