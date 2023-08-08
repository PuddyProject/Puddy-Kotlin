package world.puddy.domain.user

import world.puddy.core.domain.user.application.port.`in`.JoinUserCommand
import world.puddy.core.domain.user.application.port.`in`.LoginUserCommand
import world.puddy.core.domain.user.domain.Password
import world.puddy.core.domain.user.domain.User
import world.puddy.core.domain.user.domain.UserRole
import world.puddy.core.global.jwt.CreateTokenCommand
import world.puddy.core.global.jwt.LoginToken

object UserFixture {
    fun joinUserCommand(email: String) = JoinUserCommand(
        account = "test",
        username = "test",
        email = email,
        password = "1234",
        notificated = true,
    )

    fun loginUserCommand(account: String, password: String): LoginUserCommand {
        return LoginUserCommand(
            account = account,
            password = password,
        )
    }

    fun user(password: String) = User(
        id = 1L,
        account = "test",
        username = "test",
        email = "test@test.com",
        password = Password(password),
        notificated = true
    )

    fun createTokenRequest() = CreateTokenCommand(
        account = "test",
        username = "test",
        nickname = "test",
        role = UserRole.ROLE_USER.name,
        id = 1L,
        email = "test@test.com",
    )

    fun loginToken() = LoginToken(
        accessToken = "Bearer this-is-access-token",
        refreshToken = "Bearer this-is-refresh-token",
    )
}
