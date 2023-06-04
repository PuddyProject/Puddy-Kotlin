package world.puddy.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun existsByAccount(account : String) : Boolean

    fun findByAccount(account : String) : User?

}