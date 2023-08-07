package world.puddy.core.domain.question.application.port.out

import org.springframework.data.domain.Pageable
import world.puddy.core.domain.question.domain.Question

interface FindQuestionListPort {
    fun findQuestionList(page: Pageable, keyword: String, sort: String): List<Question>
}
