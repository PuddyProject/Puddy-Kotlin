package world.puddy.core.domain.question.application.service

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.question.application.port.`in`.FindQuestionListUseCase
import world.puddy.core.domain.question.application.port.out.FindQuestionListPort

@Service
@Transactional(readOnly = true)
class FindQuestionListService(
    private val findQuestionListPort: FindQuestionListPort,
) : FindQuestionListUseCase {
    override fun findQuestionList(page: Pageable, keyword: String, sort: String): List<FindQuestionResponse> =
        findQuestionListPort.findQuestionList(page, keyword, sort).let {
            it.map { question -> FindQuestionResponse.of(question) }
        }
}
