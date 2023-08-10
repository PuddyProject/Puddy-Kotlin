package world.puddy.core.domain.image.adapter.out.s3

import org.springframework.web.multipart.MultipartFile

data class UploadFileCommand(
    val file: MultipartFile,
    val fileName: String,
    val folderName: String
) {
    companion object {
        @JvmStatic
        fun of(file: MultipartFile, fileName: String, folderName: String): UploadFileCommand {
            return UploadFileCommand(file, fileName, folderName)
        }
    }
}
