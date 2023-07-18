package world.puddy.question

import world.puddy.question.adapter.`in`.web.FindQuestionResponse
import world.puddy.question.domain.Category
import world.puddy.question.domain.Question
import java.time.LocalDateTime

object QuestionSnippets {

    fun question(): Question {
        return Question(
            id = 1L,
            memberId = 1L,
            title = "title",
            content = "content",
            category = Category.산책,
            postCategory = 1,
        )
    }

    fun findQuestionResponse(): FindQuestionResponse {
        return FindQuestionResponse(
            id = 1L,
            title = "title",
            content = "content",
            category = "산책",
            postCategory = 1,
            createdDate = LocalDateTime.of(2021, 7, 17, 14, 38, 42)
        )
    }
}
