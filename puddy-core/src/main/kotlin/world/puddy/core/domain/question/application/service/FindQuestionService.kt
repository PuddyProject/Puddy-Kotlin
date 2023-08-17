package world.puddy.core.domain.question.application.service

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.question.application.port.`in`.FindQuestionUseCase
import world.puddy.core.domain.question.application.port.out.FindQuestionPort

@Service
@Transactional(readOnly = true)
class FindQuestionService(
    private val findQuestionPort: FindQuestionPort,
) : FindQuestionUseCase {
    override fun findQuestion(id: Long): FindQuestionResponse =
        FindQuestionResponse.of(findQuestionPort.findQuestion(id))

    override fun findQuestionList(page: Pageable, keyword: String, sort: String): FindQuestionListResponse =
        findQuestionPort.findQuestionList(page, keyword, sort).let {
            val responseDto = it.map { question -> FindQuestionResponse.of(question) }
            FindQuestionListResponse.from(responseDto)
        }
}
