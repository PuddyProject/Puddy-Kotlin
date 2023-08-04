package world.puddy.core.domain.question.application.port.out

import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionCommand


interface RegisterQuestionPort {

    fun registerQuestion(command: RegisterQuestionCommand): Long
}
