package world.puddy.core.domain.user.application.port.out

import world.puddy.core.domain.user.domain.User

interface FindUserPort {
    fun getUserById(id: Long): User
    fun getUserByAccount(account: String): User
}