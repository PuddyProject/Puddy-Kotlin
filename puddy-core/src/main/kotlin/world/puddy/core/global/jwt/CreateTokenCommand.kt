package world.puddy.core.global.jwt

data class CreateTokenCommand(
    val id: Long,
    val username: String,
    val nickname: String,
    val account: String,
    val email: String,
    val role: String,
) {
    companion object {
        @JvmStatic
        fun from(
            id: Long,
            username: String,
            nickname: String,
            account: String,
            email: String,
            role: String,
        ) = CreateTokenCommand(
            id = id,
            username = username,
            nickname = nickname,
            account = account,
            email = email,
            role = role,
        )
    }
}
