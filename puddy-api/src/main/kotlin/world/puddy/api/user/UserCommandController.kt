package world.puddy.api.user

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import world.puddy.core.domain.user.application.port.`in`.JoinUserUseCase
import world.puddy.core.domain.user.application.port.`in`.LoginUserUseCase

@RestController
@RequestMapping("/users")
class UserCommandController(
    private val joinUserUseCase: JoinUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
) {
    @PostMapping("/join")
    fun join(@RequestBody request: JoinUserRequest) {
        joinUserUseCase.join(request.toCommand())
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginUserRequest) {
        loginUserUseCase.login(request.toCommand())
    }
}
