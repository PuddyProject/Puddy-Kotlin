package world.puddy.api.question

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import world.puddy.common.response.Response
import world.puddy.core.domain.question.application.port.`in`.FindQuestionUseCase
import world.puddy.core.domain.question.application.service.FindQuestionResponse

@RestController
@RequestMapping("/questions")
class FindQuestionController(
    private val findQuestionUseCase: FindQuestionUseCase,
) {

    @GetMapping("/{id}")
    fun findQuestion(@PathVariable("id") id: Long): Response<FindQuestionResponse> =
        Response.ok(findQuestionUseCase.findQuestion(id))
}
