package world.puddy.user.presentation

import world.puddy.user.application.RegisterUserCommand

data class RegisterUserRequest(
    val account: String,
    val password: String,
    val username: String,
    val email: String,
) {
    val toCommand: RegisterUserCommand =
        RegisterUserCommand(
            account = account,
            password = password,
            username = username,
            email = email,
        )
}
