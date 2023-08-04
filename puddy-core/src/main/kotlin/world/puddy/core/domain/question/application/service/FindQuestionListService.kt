package world.puddy.core.domain.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.question.application.port.out.FindQuestionListPort

@Service
@Transactional(readOnly = true)
class FindQuestionListService(
    private val findQuestionListPort: FindQuestionListPort,
) : world.puddy.core.domain.question.application.port.`in`.FindQuestionListUseCase {
    override fun findQuestionList(): List<FindQuestionResponse> =
        findQuestionListPort.findQuestionList().let {
            it.map { question -> FindQuestionResponse.of(question) }
        }
}
