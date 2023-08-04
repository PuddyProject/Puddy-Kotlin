package world.puddy.core.domain.user

import world.puddy.core.domain.user.domain.Password

data class LoginUserCommand(
    val account: String,
    val password: Password,
) {
}