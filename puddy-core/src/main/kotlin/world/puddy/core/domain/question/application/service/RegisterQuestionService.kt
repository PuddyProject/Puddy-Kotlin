package world.puddy.core.domain.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.image.application.port.`in`.SaveImageUseCase
import world.puddy.core.domain.image.application.port.out.SaveImageCommand
import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionUseCase
import world.puddy.core.domain.question.application.port.out.RegisterQuestionPort

@Service
@Transactional
class RegisterQuestionService(
    private val registerQuestionPort: RegisterQuestionPort,
    private val saveImageUseCase: SaveImageUseCase,
) : RegisterQuestionUseCase {

    override fun registerQuestion(command: RegisterQuestionCommand): Long {
        val question = registerQuestionPort.registerQuestion(command.toEntity())
        command.images?.let {
            val imageList = saveImageUseCase.saveImage(SaveImageCommand.of(it))
            question.addImage(imageList)
        }
        return question.id
    }
}
