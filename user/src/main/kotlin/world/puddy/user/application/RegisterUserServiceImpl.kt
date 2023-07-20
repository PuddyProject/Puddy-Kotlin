package world.puddy.user.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.user.domain.UserRepository

@Service
@Transactional
class RegisterUserServiceImpl(
    private val userRepository: UserRepository
) : RegisterUserService {
    override fun registerUser(command: RegisterUserCommand) =
        userRepository.save(command.toEntity)
}
