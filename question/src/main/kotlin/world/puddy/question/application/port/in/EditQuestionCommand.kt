package world.puddy.question.application.port.`in`

data class EditQuestionCommand(
    val title: String,
    val content: String,
    val category: String,
)
