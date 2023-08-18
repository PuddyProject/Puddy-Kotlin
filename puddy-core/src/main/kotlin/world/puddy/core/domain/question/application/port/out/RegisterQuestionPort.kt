package world.puddy.core.domain.question.application.port.out

import world.puddy.core.domain.question.domain.Question

interface RegisterQuestionPort {

    fun registerQuestion(question: Question): Question
}
