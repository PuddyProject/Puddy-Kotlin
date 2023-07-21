package world.puddy.user.application

import world.puddy.user.domain.Password
import world.puddy.user.domain.User

data class RegisterUserCommand(
    val account: String,
    val password: Password,
    val username: String,
    val email: String,
) {
    val toEntity: User
        get() = User(
            account = account,
            password = password,
            username = username,
            email = email,
            nickname = "퍼디1234",
        )
}
