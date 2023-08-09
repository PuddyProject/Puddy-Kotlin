package world.puddy.core.domain.question.application.port.`in`

interface DeleteQuestionUseCase {
    fun deleteQuestion(command: DeleteQuestionCommand)
}
