package world.puddy.core.domain.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.question.application.port.`in`.EditQuestionCommand
import world.puddy.core.domain.question.application.port.`in`.EditQuestionUseCase
import world.puddy.core.domain.question.application.port.out.FindQuestionPort

@Service
@Transactional
class EditQuestionService(
    private val findQuestionPort: FindQuestionPort
) : EditQuestionUseCase {
    override fun editQuestion(command: EditQuestionCommand): Long {
        val question = findQuestionPort.findQuestion(command.questionId)
        question.assertOwnedBy(command.memberId)
        return question.edit(command.title, command.content, command.category)
    }
}
