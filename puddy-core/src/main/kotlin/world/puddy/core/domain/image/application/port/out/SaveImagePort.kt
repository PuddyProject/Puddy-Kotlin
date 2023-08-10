package world.puddy.core.domain.image.application.port.out

import world.puddy.core.domain.image.domain.Image

interface SaveImagePort {
    fun saveImage(image: Image)
}
