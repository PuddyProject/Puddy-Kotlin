package world.puddy.core.domain.user.adapter.out.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import world.puddy.core.domain.user.application.port.out.FindUserPort
import world.puddy.core.domain.user.application.port.out.JoinUserPort
import world.puddy.core.domain.user.domain.User
import world.puddy.core.global.error.exception.UserNotFoundException

@Repository
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository
) : JoinUserPort, FindUserPort {
    override fun saveUser(user: User) = userJpaRepository.save(user).id

    override fun getUserById(id: Long): User {
        return userJpaRepository.findByIdOrNull(id) ?: throw UserNotFoundException()
    }

    override fun existsByAccount(account: String): Boolean {
        return userJpaRepository.existsByInformationAccount(account)
    }

    override fun getUserByAccount(account: String): User {
        return userJpaRepository.findByInformationAccount(account) ?: throw UserNotFoundException()
    }
}
