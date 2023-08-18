package world.puddy.core.domain.question.application.port.out

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import world.puddy.core.domain.question.domain.Question

interface FindQuestionPort {
    fun findQuestion(id: Long): Question

    fun findQuestionList(page: Pageable, keyword: String, sort: String): Slice<Question>
}
