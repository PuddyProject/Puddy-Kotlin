package world.puddy.question.application.port.out

import world.puddy.question.domain.Question

interface FindQuestionListPort {
    fun findQuestionList(): List<Question>
}
