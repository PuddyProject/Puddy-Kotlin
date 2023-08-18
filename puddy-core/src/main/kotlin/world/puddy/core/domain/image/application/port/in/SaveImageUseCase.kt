package world.puddy.core.domain.image.application.port.`in`

import world.puddy.core.domain.image.application.port.out.SaveImageCommand
import world.puddy.core.domain.image.domain.Image

interface SaveImageUseCase {

    fun saveImage(command: SaveImageCommand): List<Image>
}
