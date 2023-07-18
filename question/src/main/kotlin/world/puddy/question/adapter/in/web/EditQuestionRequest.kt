package world.puddy.question.adapter.`in`.web

import world.puddy.question.application.port.`in`.EditQuestionCommand

data class EditQuestionRequest(
    val title: String,
    val content: String,
    val category: String,
) {
    fun toCommand() = EditQuestionCommand(
        title = title,
        content = content,
        category = category,
    )
}
