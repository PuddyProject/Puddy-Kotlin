package world.puddy.api.question

import jakarta.validation.constraints.NotBlank
import org.springframework.web.multipart.MultipartFile
import world.puddy.core.domain.question.application.port.`in`.EditQuestionCommand
import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.core.domain.question.domain.Category

data class RegisterQuestionRequest(
    @NotBlank
    val title: String,
    @NotBlank
    val content: String,

    val category: Category,

    val postCategory: Int,
) {
    fun toCommand(memberId: Long, images: List<MultipartFile>?) = RegisterQuestionCommand(
        memberId = memberId,
        title = title,
        content = content,
        category = category,
        postCategory = postCategory,
        images = images,
    )
}

data class EditQuestionRequest(
    val title: String,
    val content: String,
    val category: String,
    val images: List<MultipartFile>?,
) {
    fun toCommand(questionId: Long, memberId: Long, images: List<MultipartFile>?) = EditQuestionCommand(
        title = title,
        content = content,
        category = category,
        questionId = questionId,
        memberId = memberId,
        images = images,
    )
}
