package world.puddy.question.application.port.`in`

import world.puddy.question.adapter.`in`.web.FindQuestionResponse

interface FindQuestionListUseCase {
    fun findQuestionList(): List<FindQuestionResponse>
}
