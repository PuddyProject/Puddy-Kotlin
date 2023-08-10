package world.puddy.core.domain.image.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.image.application.port.`in`.FindImageUseCase
import world.puddy.core.domain.image.application.port.out.FindImagePort
import world.puddy.core.domain.image.domain.Image

@Service
@Transactional
class FindImageService(
    private val findImagePort: FindImagePort
) : FindImageUseCase {

    @Transactional(readOnly = true)
    override fun findImage(imageId: Long): Image {
        return findImagePort.findImage(imageId)
    }
}
