package world.puddy.question.adapter.`in`.web

data class FindQuestionResponse(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
    val postCategory: Int,
)
