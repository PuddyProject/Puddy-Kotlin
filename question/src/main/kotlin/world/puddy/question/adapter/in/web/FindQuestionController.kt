package world.puddy.question.adapter.`in`.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import world.puddy.common.response.Response
import world.puddy.question.application.port.`in`.FindQuestionUseCase

@RestController
@RequestMapping("/questions")
class FindQuestionController(
    private val findQuestionUseCase: FindQuestionUseCase,
) {

    @GetMapping("/{id}")
    fun findQuestion(@PathVariable("id") id: Long): Response<FindQuestionResponse> =
        Response.ok(findQuestionUseCase.findQuestion(id))
}
