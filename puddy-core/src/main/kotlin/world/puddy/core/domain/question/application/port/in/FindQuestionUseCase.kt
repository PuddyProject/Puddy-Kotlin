package world.puddy.core.domain.question.application.port.`in`

import org.springframework.data.domain.Pageable
import world.puddy.core.domain.question.application.service.FindQuestionResponse

interface FindQuestionUseCase {
    fun findQuestion(id: Long): FindQuestionResponse

    fun findQuestionList(page: Pageable, keyword: String, sort: String): List<FindQuestionResponse>
}
