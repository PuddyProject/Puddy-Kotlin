package world.puddy.user.domain

interface UserRepository {

    fun save(user: User): User

    fun findById(id: Long): User

    fun existsByEmail(email: String): Boolean
}
