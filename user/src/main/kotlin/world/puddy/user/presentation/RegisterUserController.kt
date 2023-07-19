package world.puddy.user.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import world.puddy.user.application.RegisterUserService

@RestController
@RequestMapping("/users")
class RegisterUserController(
    private val registerUserService: RegisterUserService
) {

    @PostMapping
    fun registerMember(
        @RequestBody request: RegisterUserRequest
    ) {
        registerUserService.registerUser(request.toCommand)
    }
}
