package world.puddy.question.adapter.`in`.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import world.puddy.common.response.Response
import world.puddy.question.application.port.`in`.FindQuestionListUseCase

@RestController
@RequestMapping("/questions")
class FindQuestionListController(
    private val findQuestionListUseCase: FindQuestionListUseCase,
) {
    @GetMapping
    fun findQuestionList(): Response<List<FindQuestionResponse>> =
        Response.ok(findQuestionListUseCase.findQuestionList())
}
