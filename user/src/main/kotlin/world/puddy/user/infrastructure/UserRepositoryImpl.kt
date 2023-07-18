package world.puddy.user.infrastructure

import world.puddy.user.domain.User
import world.puddy.user.domain.UserRepository

class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {
    override fun save(user: User) = userJpaRepository.save(user)

    override fun findById(id: Long): User {
        TODO("Not yet implemented")
    }
}
