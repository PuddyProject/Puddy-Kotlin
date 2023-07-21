package world.puddy.user.infrastructure

import org.springframework.stereotype.Repository
import world.puddy.user.domain.User
import world.puddy.user.domain.UserRepository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {
    override fun save(user: User) = userJpaRepository.save(user)

    override fun findById(id: Long): User {
        TODO("Not yet implemented")
    }

    override fun existsByEmail(email: String) = userJpaRepository.existsByInformationEmail(email)
}
