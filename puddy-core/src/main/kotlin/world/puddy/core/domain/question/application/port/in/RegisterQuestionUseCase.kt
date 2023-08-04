package world.puddy.core.domain.question.application.port.`in`

interface RegisterQuestionUseCase {

    fun registerQuestion(command: RegisterQuestionCommand): Long
}
