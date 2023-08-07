package world.puddy.api.user

import world.puddy.core.domain.user.application.port.`in`.JoinUserCommand
import world.puddy.core.domain.user.application.port.`in`.LoginUserCommand

data class JoinUserRequest(
    val account: String,
    val username: String,
    val email: String,
    val password: String,
    val notificated: Boolean,
) {
    fun toCommand() =
        JoinUserCommand(
            account = account,
            username = username,
            email = email,
            notificated = notificated,
            password = password
        )
}

data class LoginUserRequest(
    val account: String,
    val password: String,
) {
    fun toCommand() =
        LoginUserCommand(
            account = account,
            password = password
        )
}
