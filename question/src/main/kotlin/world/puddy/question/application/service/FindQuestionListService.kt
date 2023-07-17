package world.puddy.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.question.adapter.`in`.web.FindQuestionResponse
import world.puddy.question.application.port.`in`.FindQuestionListUseCase
import world.puddy.question.application.port.out.FindQuestionListPort

@Service
@Transactional(readOnly = true)
class FindQuestionListService(
    private val findQuestionListPort: FindQuestionListPort,
) : FindQuestionListUseCase {
    override fun findQuestionList(): List<FindQuestionResponse> =
        findQuestionListPort.findQuestionList().let {
            it.map { question -> FindQuestionResponse.of(question) }
        }
}
