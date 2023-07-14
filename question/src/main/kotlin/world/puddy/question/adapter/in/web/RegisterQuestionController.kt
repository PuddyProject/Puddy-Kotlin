package world.puddy.question.adapter.`in`.web

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import world.puddy.common.response.Response
import world.puddy.question.adapter.out.persistence.QuestionMapper
import world.puddy.question.application.port.`in`.RegisterQuestionUseCase
import world.puddy.question.domain.Question

@RestController
@RequestMapping("/questions")
class RegisterQuestionController(
    private val registerQuestionUseCase: RegisterQuestionUseCase
) {

    @PostMapping
    fun register(@Valid @RequestBody request: RegisterQuestionRequest): Response<Question> {
        val command = QuestionMapper.toCommand(request)
        return Response.ok(registerQuestionUseCase.registerQuestion(command))
    }
}
