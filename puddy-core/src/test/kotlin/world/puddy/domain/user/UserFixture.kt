package world.puddy.domain.user

import world.puddy.core.domain.user.application.port.`in`.JoinUserCommand

object UserFixture {
    fun joinUserCommand(email: String) = JoinUserCommand(
        account = "test",
        username = "test",
        email = email,
        password = "1234",
        notificated = true,
    )
}
