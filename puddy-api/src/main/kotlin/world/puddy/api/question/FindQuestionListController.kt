package world.puddy.api.question

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import world.puddy.common.response.Response
import world.puddy.core.domain.question.application.port.`in`.FindQuestionListUseCase
import world.puddy.core.domain.question.application.service.FindQuestionResponse

@RestController
@RequestMapping("/questions")
class FindQuestionListController(
    private val findQuestionListUseCase: FindQuestionListUseCase,
) {
    @GetMapping
    fun findQuestionList(): Response<List<FindQuestionResponse>> =
        Response.ok(findQuestionListUseCase.findQuestionList())
}
