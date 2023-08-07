package world.puddy.core.domain.user.application.port.`in`

import world.puddy.core.global.jwt.LoginToken

interface LoginUserUseCase {
    fun login(command: LoginUserCommand): LoginToken
}
