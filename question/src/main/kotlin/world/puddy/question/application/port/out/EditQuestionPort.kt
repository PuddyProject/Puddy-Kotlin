package world.puddy.question.application.port.out

import world.puddy.question.application.port.`in`.EditQuestionCommand

interface EditQuestionPort {
    fun editQuestion(id: Long, command: EditQuestionCommand): Long
}
