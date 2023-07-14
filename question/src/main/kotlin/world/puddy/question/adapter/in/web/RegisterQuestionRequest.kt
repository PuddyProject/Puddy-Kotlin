package world.puddy.question.adapter.`in`.web

import jakarta.validation.constraints.NotBlank

data class RegisterQuestionRequest(

    val memberId: Long,
    @NotBlank(message = "질문 제목은 빈칸일 수 없습니다.")
    val title: String,
    @NotBlank(message = "질문 내용은 빈칸일 수 없습니다.")
    val content: String,
)
