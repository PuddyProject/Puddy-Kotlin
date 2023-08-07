package world.puddy.core.domain.user.application.port.out

import world.puddy.core.domain.user.domain.User

interface JoinUserPort {
    fun saveUser(user: User): Long
}
