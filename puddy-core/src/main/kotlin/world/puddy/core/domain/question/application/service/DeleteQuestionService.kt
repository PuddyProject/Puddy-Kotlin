package world.puddy.core.domain.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.question.application.port.`in`.DeleteQuestionCommand
import world.puddy.core.domain.question.application.port.`in`.DeleteQuestionUseCase
import world.puddy.core.domain.question.application.port.out.DeleteQuestionPort
import world.puddy.core.domain.question.application.port.out.FindQuestionPort

@Service
@Transactional
class DeleteQuestionService(
    private val deleteQuestionPort: DeleteQuestionPort,
    private val findQuestionPort: FindQuestionPort
) : DeleteQuestionUseCase {
    override fun deleteQuestion(command: DeleteQuestionCommand) {
        val question = findQuestionPort.findQuestion(command.questionId)
        question.verify(command.memberId)
        deleteQuestionPort.deleteQuestion(command.questionId)
    }
}
