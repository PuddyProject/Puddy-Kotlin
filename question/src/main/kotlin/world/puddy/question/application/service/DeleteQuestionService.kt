package world.puddy.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.question.application.port.`in`.DeleteQuestionUseCase
import world.puddy.question.application.port.out.DeleteQuestionPort

@Service
@Transactional
class DeleteQuestionService(
    private val deleteQuestionPort: DeleteQuestionPort
) : DeleteQuestionUseCase {
    override fun deleteQuestion(id: Long) {
        deleteQuestionPort.deleteQuestion(id)
    }
}
