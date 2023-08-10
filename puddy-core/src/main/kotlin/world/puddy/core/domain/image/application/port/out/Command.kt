package world.puddy.core.domain.image.application.port.out

import org.springframework.web.multipart.MultipartFile

data class SaveImageCommand(
    val images: List<MultipartFile>,
) {

    companion object {
        @JvmStatic
        fun of(images: List<MultipartFile>): SaveImageCommand {
            return SaveImageCommand(images)
        }
    }
}
