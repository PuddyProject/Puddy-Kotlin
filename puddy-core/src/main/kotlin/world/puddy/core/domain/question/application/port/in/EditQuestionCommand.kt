package world.puddy.core.domain.question.application.port.`in`

data class EditQuestionCommand(
    val title: String,
    val content: String,
    val category: String,
)
