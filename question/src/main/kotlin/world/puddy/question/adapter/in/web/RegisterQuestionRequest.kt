package world.puddy.question.adapter.`in`.web

import jakarta.validation.constraints.NotBlank
import world.puddy.question.domain.Category

data class RegisterQuestionRequest(
    @NotBlank
    val title: String,
    @NotBlank
    val content: String,

    val category: Category,

    val postCategory: Int,
)
