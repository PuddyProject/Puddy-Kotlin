package world.puddy.question.adapter.`in`.web

import world.puddy.question.domain.Question
import world.puddy.question.domain.validator.Category

data class FindQuestionResponse(
    val id: Long,
    val title: String,
    val content: String,
    @Category
    val category: String,
    val postCategory: Int,
) {
    companion object {
        @JvmStatic
        fun of(question: Question): FindQuestionResponse {
            return FindQuestionResponse(
                id = question.id,
                title = question.title,
                content = question.content,
                category = question.category.name,
                postCategory = question.postCategory
            )
        }
    }
}
