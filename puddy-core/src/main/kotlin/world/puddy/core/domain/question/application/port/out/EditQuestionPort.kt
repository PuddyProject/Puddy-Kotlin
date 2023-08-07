package world.puddy.core.domain.question.application.port.out

import world.puddy.core.domain.question.application.port.`in`.EditQuestionCommand

interface EditQuestionPort {
    fun editQuestion(command: EditQuestionCommand): Long
}
