package world.puddy.core.domain.user.application.port.`in`

import world.puddy.core.domain.user.domain.Password
import world.puddy.core.domain.user.domain.User

data class JoinUserCommand(
    val account: String,
    val username: String,
    val email: String,
    val password: String,
    val notificated: Boolean,
) {
    fun toEntity() =
        User(
            account = account,
            username = username,
            email = email,
            notificated = notificated,
            password = Password(password)
        )
}

data class LoginUserCommand(
    val account: String,
    val password: String,
)
