package world.puddy.question.application.port.`in`

import world.puddy.question.adapter.`in`.web.FindQuestionResponse

interface FindQuestionUseCase {

    fun findQuestion(id: Long): FindQuestionResponse
}
