package world.puddy.core.domain.question.application.port.out

import world.puddy.core.domain.question.domain.Question

interface FindQuestionListPort {
    fun findQuestionList(): List<Question>
}
