package world.puddy.question.adapter.out.persistence

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import world.puddy.question.adapter.`in`.web.RegisterQuestionRequest
import world.puddy.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.question.domain.Question

@Component
class QuestionMapper {

    fun toCommand(request: RegisterQuestionRequest, images: List<MultipartFile>?): RegisterQuestionCommand {
        return RegisterQuestionCommand(
            memberId = request.memberId,
            title = request.title,
            content = request.content,
            category = request.category,
            postCategory = request.postCategory,
            images = images
        )
    }

    fun toEntity(command: RegisterQuestionCommand): Question {
        return Question(
            memberId = command.memberId,
            title = command.title,
            content = command.content,
            category = command.category,
            postCategory = command.postCategory

        )
    }
}
