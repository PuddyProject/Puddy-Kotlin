package world.puddy.user.application

interface RegisterUserService {
    fun registerUser(command: RegisterUserCommand): Long
}
