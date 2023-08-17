package world.puddy.core.domain.question.application.service

import org.springframework.data.domain.Slice

data class FindQuestionListResponse(
    val questionList: List<FindQuestionResponse>,
    val hasNext: Boolean
) {
    companion object {
        @JvmStatic
        fun from(questionSlice: Slice<FindQuestionResponse>) =
            FindQuestionListResponse(questionSlice.content, questionSlice.hasNext())
    }
}
