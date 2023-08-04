package world.puddy.api.question

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import world.puddy.common.response.Response
import world.puddy.core.domain.question.application.port.`in`.EditQuestionUseCase

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
    ) = Response.ok(editQuestionUseCase.editQuestion(questionId, request.toCommand()))
}
