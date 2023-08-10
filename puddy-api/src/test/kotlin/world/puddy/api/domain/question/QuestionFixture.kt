package world.puddy.api.domain.question

import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.core.domain.question.domain.Category

object QuestionFixture {

    fun registerQuestionCommand(): RegisterQuestionCommand {
        return RegisterQuestionCommand(
            title = "질문글 제목",
            content = "질문글 내용",
            category = Category.건강,
            postCategory = 1,
            images = null
        )
    }
}