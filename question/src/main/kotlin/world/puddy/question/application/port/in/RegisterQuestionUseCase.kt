package world.puddy.question.application.port.`in`

import world.puddy.question.domain.Question

interface RegisterQuestionUseCase {

    fun registerQuestion(command: RegisterQuestionCommand): Question
}
