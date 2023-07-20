package world.puddy.question.application.port.out

import world.puddy.question.application.port.`in`.RegisterQuestionCommand

interface RegisterQuestionPort {

    fun registerQuestion(command: RegisterQuestionCommand): Long
}
