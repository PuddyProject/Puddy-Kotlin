package world.puddy.question.application.port.`in`

import org.springframework.web.multipart.MultipartFile
import world.puddy.question.domain.Category

class RegisterQuestionCommand(
    val memberId: Long,
    val title: String,
    val content: String,
    val category: Category,
    val postCategory: Int,
    val images: List<MultipartFile>?
)
