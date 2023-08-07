package world.puddy.api.question

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionUseCase
import world.puddy.core.global.response.Response

@RestController
@RequestMapping("/questions")
class RegisterQuestionController(
    private val registerQuestionUseCase: RegisterQuestionUseCase,
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE])
    fun register(
        @RequestPart("request") request: RegisterQuestionRequest,
        @RequestPart(value = "images", required = false) images: List<MultipartFile>?
    ): Response<Long> {
        request.toCommand(
            memberId = 1L,
            images = emptyList()
        ).also { command ->
            return Response.ok(registerQuestionUseCase.registerQuestion(command))
        }
    }
}
