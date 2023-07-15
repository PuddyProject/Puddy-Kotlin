package world.puddy.question.adapter.`in`.web

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import world.puddy.common.response.Response
import world.puddy.question.adapter.out.persistence.QuestionMapper
import world.puddy.question.application.port.`in`.RegisterQuestionUseCase
import world.puddy.question.domain.Question

@RestController
@RequestMapping("/questions")
class RegisterQuestionController(
    private val registerQuestionUseCase: RegisterQuestionUseCase,
    private val questionMapper: QuestionMapper
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE])
    fun register(
        @RequestPart("request") request: RegisterQuestionRequest,
        @RequestPart(value = "images", required = false) images: List<MultipartFile>?
    ): Response<Question> {
        val command = questionMapper.toCommand(request, images)
        return Response.ok(registerQuestionUseCase.registerQuestion(command))
    }
}
