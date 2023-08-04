package world.puddy.core.domain.question.application.port.`in`

interface EditQuestionUseCase {
    fun editQuestion(id: Long, command: EditQuestionCommand): Long
}
