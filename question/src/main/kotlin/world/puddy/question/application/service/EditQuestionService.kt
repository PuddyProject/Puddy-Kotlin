package world.puddy.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.question.application.port.`in`.EditQuestionCommand
import world.puddy.question.application.port.`in`.EditQuestionUseCase
import world.puddy.question.application.port.out.EditQuestionPort

@Service
@Transactional
class EditQuestionService(
    private val editQuestionPort: EditQuestionPort
) : EditQuestionUseCase {
    override fun editQuestion(id: Long, command: EditQuestionCommand) = editQuestionPort.editQuestion(id, command)
}
