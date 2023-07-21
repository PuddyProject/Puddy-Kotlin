package world.puddy.user.presentation

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import world.puddy.user.application.RegisterUserCommand
import world.puddy.user.domain.Password

data class RegisterUserRequest(
    val account: String,
    @Pattern(regexp = "[가-힣]{1,30}", message = "올바른 형식의 이름이어야 합니다")
    val username: String,

    @Email
    val email: String,

    val password: Password,
) {
    val toCommand: RegisterUserCommand =
        RegisterUserCommand(
            account = account,
            password = password,
            username = username,
            email = email,
        )
}
