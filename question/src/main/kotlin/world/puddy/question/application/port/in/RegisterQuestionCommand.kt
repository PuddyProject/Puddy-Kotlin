package world.puddy.question.application.port.`in`

import org.springframework.web.multipart.MultipartFile

class RegisterQuestionCommand(
    val memberId: Long,
    val title: String,
    val content: String,
    val images: List<MultipartFile>?
)
