package world.puddy.question

import world.puddy.question.adapter.`in`.web.FindQuestionResponse
import world.puddy.question.application.port.`in`.EditQuestionCommand
import world.puddy.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.question.domain.Category
import world.puddy.question.domain.Question
import java.time.LocalDateTime

object QuestionSnippets {

    val question = Question(
        id = 1L,
        memberId = 1L,
        title = "title",
        content = "content",
        category = Category.산책,
        postCategory = 1,
    )

    val findQuestionResponse = FindQuestionResponse(
        id = 1L,
        title = "title",
        content = "content",
        category = "산책",
        postCategory = 1,
        createdDate = LocalDateTime.of(2021, 7, 17, 14, 38, 42)
    )

    val registerQuestionCommand = RegisterQuestionCommand(
        title = "title",
        content = "content",
        category = Category.산책,
        postCategory = 1,
        images = null
    )

    val editQuestionCommand = EditQuestionCommand(
        title = "변경 제목",
        content = "변경 내용",
        category = "먹이",
    )
}
