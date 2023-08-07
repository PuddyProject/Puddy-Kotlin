package world.puddy.api.question

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import world.puddy.core.domain.question.application.port.`in`.FindQuestionUseCase
import world.puddy.core.domain.question.application.service.FindQuestionResponse
import world.puddy.core.global.response.Response

@RestController
@RequestMapping("/questions")
class FindQuestionController(
    private val findQuestionUseCase: FindQuestionUseCase,
) {

    @GetMapping("/{questionId}")
    fun findQuestion(@PathVariable("questionId") id: Long): Response<FindQuestionResponse> =
        Response.ok(findQuestionUseCase.findQuestion(id))

    @GetMapping
    fun findQuestionList(
        @RequestParam(value = "keyword", defaultValue = "") keyword: String,
        @RequestParam(value = "sort", defaultValue = "desc") sort: String,
        @RequestParam(value = "page", defaultValue = "0") page: Int,
    ): Response<List<FindQuestionResponse>> {
        val pageable: Pageable = PageRequest.of(page - DEFAULT_PAGE, DEFAULT_PAGE_SIZE)
        return Response.ok(findQuestionUseCase.findQuestionList(pageable, keyword, sort))
    }

    companion object {
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_PAGE_SIZE = 10
    }
}
