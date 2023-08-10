package world.puddy.core.domain.image.application.port.`in`

import world.puddy.core.domain.image.domain.Image

interface FindImageUseCase {

    fun findImage(imageId: Long): Image
}
