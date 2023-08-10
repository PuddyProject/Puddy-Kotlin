package world.puddy.core.domain.image.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.image.adapter.out.s3.S3UpdateAdapter
import world.puddy.core.domain.image.adapter.out.s3.UploadFileCommand
import world.puddy.core.domain.image.application.port.`in`.SaveImageUseCase
import world.puddy.core.domain.image.application.port.out.SaveImageCommand
import world.puddy.core.domain.image.application.port.out.SaveImagePort
import world.puddy.core.domain.image.domain.Image

@Service
@Transactional
class SaveImageService(
    private val saveImagePort: SaveImagePort,
    private val s3UpdateAdapter: S3UpdateAdapter,
) : SaveImageUseCase {

    override fun saveImage(command: SaveImageCommand) {
        command.images.forEach {
            val imageInformation = s3UpdateAdapter.uploadToS3(
                UploadFileCommand.of(it, it.originalFilename!!, QUESTION_FOLDER)
            )
            Image(
                imagePath = imageInformation.imagePath,
                originalName = it.originalFilename!!,
                storedName = imageInformation.storedName
            ).let { image -> saveImagePort.saveImage(image) }
        }
    }

    companion object {
        private const val QUESTION_FOLDER = "questions"
    }
}
