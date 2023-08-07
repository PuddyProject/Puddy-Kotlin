package world.puddy.core.domain.user.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import world.puddy.core.domain.user.domain.User

interface UserJpaRepository : JpaRepository<User, Long> {
    fun findByAccount(account: String): User?
}
