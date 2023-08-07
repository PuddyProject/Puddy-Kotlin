package world.puddy.core.domain.user.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import world.puddy.core.domain.user.domain.User

interface UserRepository : JpaRepository<User, Long>
