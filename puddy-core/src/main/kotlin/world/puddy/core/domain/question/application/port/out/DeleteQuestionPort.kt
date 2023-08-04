package world.puddy.core.domain.question.application.port.out

interface DeleteQuestionPort {
    fun deleteQuestion(id: Long)
}
