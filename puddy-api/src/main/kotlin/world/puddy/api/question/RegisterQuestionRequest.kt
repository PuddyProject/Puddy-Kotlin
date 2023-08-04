package world.puddy.api.question

import jakarta.validation.constraints.NotBlank
import world.puddy.core.domain.question.domain.Category

data class RegisterQuestionRequest(
    @NotBlank
    val title: String,
    @NotBlank
    val content: String,

    val category: Category,

    val postCategory: Int,
)
