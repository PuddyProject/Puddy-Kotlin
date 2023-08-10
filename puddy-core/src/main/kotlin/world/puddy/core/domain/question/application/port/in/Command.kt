package world.puddy.core.domain.question.application.port.`in`

import org.springframework.web.multipart.MultipartFile
import world.puddy.core.domain.question.domain.Category
import world.puddy.core.domain.question.domain.Question

data class RegisterQuestionCommand(
    val memberId: Long = 0L,
    val title: String,
    val content: String,
    val category: Category,
    val postCategory: Int,
    val images: List<MultipartFile>?
) {
    fun toEntity() =
        Question(
            memberId = this.memberId,
            title = this.title,
            content = this.content,
            category = this.category,
            postCategory = this.postCategory
        )
}

data class EditQuestionCommand(
    val title: String,
    val content: String,
    val category: String,
    val memberId: Long,
    val questionId: Long,
    val images: List<MultipartFile>?
)

data class DeleteQuestionCommand(
    val questionId: Long,
    val memberId: Long
) {
    companion object {
        @JvmStatic
        fun from(questionId: Long, memberId: Long) =
            DeleteQuestionCommand(
                questionId = questionId,
                memberId = memberId
            )
    }
}
