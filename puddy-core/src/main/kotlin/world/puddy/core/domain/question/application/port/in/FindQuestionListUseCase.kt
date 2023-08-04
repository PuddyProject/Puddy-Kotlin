package world.puddy.core.domain.question.application.port.`in`

import world.puddy.core.domain.question.application.service.FindQuestionResponse


interface FindQuestionListUseCase {
    fun findQuestionList(): List<FindQuestionResponse>
}
