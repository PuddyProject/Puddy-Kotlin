package world.puddy.question.application.port.out

import world.puddy.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.question.domain.Question

interface RegisterQuestionPort {

    fun registerQuestion(command: RegisterQuestionCommand): Question
}
