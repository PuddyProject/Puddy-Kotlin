package world.puddy.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.question.adapter.`in`.web.FindQuestionResponse
import world.puddy.question.application.port.`in`.FindQuestionUseCase
import world.puddy.question.application.port.out.FindQuestionPort

@Service
@Transactional(readOnly = true)
class FindQuestionService(
    private val findQuestionPort: FindQuestionPort,
) : FindQuestionUseCase {
    override fun findQuestion(id: Long): FindQuestionResponse =
        FindQuestionResponse.of(findQuestionPort.findQuestion(id))
}
