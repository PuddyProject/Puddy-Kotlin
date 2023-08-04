package world.puddy.core.domain.question.application.service

import world.puddy.core.domain.question.domain.Question
import world.puddy.core.domain.question.domain.validator.Category
import java.time.LocalDateTime

data class FindQuestionResponse(
    val id: Long,
    val title: String,
    val content: String,
    @Category
    val category: String,
    val postCategory: Int,
    val createdDate: LocalDateTime,
) {
    companion object {
        @JvmStatic
        fun of(question: Question): FindQuestionResponse {
            return FindQuestionResponse(
                id = question.id,
                title = question.title,
                content = question.content,
                category = question.category.name,
                postCategory = question.postCategory,
                createdDate = question.createdDate
            )
        }
    }
}
