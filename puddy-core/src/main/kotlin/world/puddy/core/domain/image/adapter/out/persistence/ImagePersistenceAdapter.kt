package world.puddy.core.domain.image.adapter.out.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import world.puddy.core.domain.image.application.port.out.FindImagePort
import world.puddy.core.domain.image.application.port.out.SaveImagePort
import world.puddy.core.domain.image.domain.Image
import world.puddy.core.global.error.exception.ImageNotFoundException

@Repository
class ImagePersistenceAdapter(
    private val imageJpaRepository: ImageJpaRepository
) : SaveImagePort, FindImagePort {
    override fun saveImage(image: Image) {
        imageJpaRepository.save(image)
    }

    override fun findImage(imageId: Long): Image {
        return imageJpaRepository.findByIdOrNull(imageId) ?: throw ImageNotFoundException()
    }
}
