package world.puddy.core.domain.user.application.port.`in`

interface JoinUserUseCase {
    fun join(command: JoinUserCommand)
}