package world.puddy.api.question

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import world.puddy.core.domain.question.application.port.`in`.EditQuestionUseCase
import world.puddy.core.global.response.Response

@RestController
@RequestMapping("/questions")
class EditQuestionController(
    private val editQuestionUseCase: EditQuestionUseCase
) {
    @PutMapping(
        "/{questionId}",
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun editQuestion(
        @PathVariable("questionId") questionId: Long,
        @RequestPart("request") request: EditQuestionRequest,
        @RequestPart("images", required = false) images: List<MultipartFile>,
    ) = Response.ok(editQuestionUseCase.editQuestion(request.toCommand(questionId, 1L)))
}
