package world.puddy.core.domain.question.application.port.`in`

import org.springframework.web.multipart.MultipartFile
import world.puddy.core.domain.question.domain.Category

class RegisterQuestionCommand(
    val memberId: Long = 0L,
    val title: String,
    val content: String,
    val category: Category,
    val postCategory: Int,
    val images: List<MultipartFile>?
)
