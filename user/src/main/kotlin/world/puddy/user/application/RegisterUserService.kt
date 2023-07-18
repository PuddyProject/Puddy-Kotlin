package world.puddy.user.application

import world.puddy.user.domain.User

interface RegisterUserService {
    fun registerUser(command: RegisterUserCommand): User
}
