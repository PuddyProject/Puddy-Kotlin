package world.puddy.question.application.port.`in`

interface RegisterQuestionUseCase {

    fun registerQuestion(command: RegisterQuestionCommand): Long
}
