package world.puddy.question.application.port.out

import world.puddy.question.domain.Question

interface FindQuestionPort {
    fun findQuestion(id: Long): Question
}
