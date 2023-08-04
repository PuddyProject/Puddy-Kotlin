package world.puddy.core.domain.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.question.application.port.`in`.FindQuestionUseCase
import world.puddy.core.domain.question.application.port.out.FindQuestionPort

@Service
@Transactional(readOnly = true)
class FindQuestionService(
    private val findQuestionPort: FindQuestionPort,
) : world.puddy.core.domain.question.application.port.`in`.FindQuestionUseCase {
    override fun findQuestion(id: Long): FindQuestionResponse =
        FindQuestionResponse.of(findQuestionPort.findQuestion(id))
}
