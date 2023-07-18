package world.puddy.user.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import world.puddy.user.domain.User

interface UserJpaRepository: JpaRepository<User,Long>
