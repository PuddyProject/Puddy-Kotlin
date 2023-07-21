package world.puddy.user.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.user.domain.UserRepository

@Service
@Transactional
class RegisterUserServiceImpl(
    private val userRepository: UserRepository
) : RegisterUserService {
    override fun registerUser(command: RegisterUserCommand): Long {
        check(userRepository.existsByEmail(command.email).not()) {
            "이미 존재하는 이메일입니다."
        }
        val savedUser = userRepository.save(command.toEntity)
        return savedUser.id
    }
}
