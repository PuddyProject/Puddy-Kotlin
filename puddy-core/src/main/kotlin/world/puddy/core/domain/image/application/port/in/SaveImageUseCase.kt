package world.puddy.core.domain.image.application.port.`in`

import world.puddy.core.domain.image.application.port.out.SaveImageCommand

interface SaveImageUseCase {

    fun saveImage(command: SaveImageCommand)
}
