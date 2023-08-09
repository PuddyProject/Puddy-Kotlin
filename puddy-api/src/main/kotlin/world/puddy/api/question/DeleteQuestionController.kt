package world.puddy.api.question

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import world.puddy.core.domain.question.application.port.`in`.DeleteQuestionCommand

import world.puddy.core.domain.question.application.port.`in`.DeleteQuestionUseCase
import world.puddy.core.global.auth.JwtUserDetails
import world.puddy.core.global.response.Response

@RestController
@RequestMapping("/questions")
class DeleteQuestionController(
    private val deleteQuestionUseCase: DeleteQuestionUseCase
) {

    @DeleteMapping("/{questionId}")
    fun deleteQuestion(
        @PathVariable questionId: Long,
        @AuthenticationPrincipal user: JwtUserDetails
    ): Response<Unit> {
        deleteQuestionUseCase.deleteQuestion(DeleteQuestionCommand.from(questionId, user.userId!!))
        return Response.ok()
    }
}
