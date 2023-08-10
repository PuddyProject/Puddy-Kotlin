package world.puddy.core.domain.image.adapter.out.persistence

import org.springframework.stereotype.Repository
import world.puddy.core.domain.image.application.port.out.SaveImagePort
import world.puddy.core.domain.image.domain.Image

@Repository
class ImagePersistenceAdapter(
    private val imageJpaRepository: ImageJpaRepository
) : SaveImagePort {
    override fun saveImage(image: Image) {
        imageJpaRepository.save(image)
    }
}
