package world.puddy.question.adapter.`in`.web

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import world.puddy.common.response.Response
import world.puddy.question.application.port.`in`.DeleteQuestionUseCase

@RestController
@RequestMapping("/questions")
class DeleteQuestionController(
    private val deleteQuestionUseCase: DeleteQuestionUseCase
) {

    @DeleteMapping("/{questionId}")
    fun deleteQuestion(@PathVariable questionId: Long): Response<Unit> {
        deleteQuestionUseCase.deleteQuestion(questionId)
        return Response.ok()
    }
}
