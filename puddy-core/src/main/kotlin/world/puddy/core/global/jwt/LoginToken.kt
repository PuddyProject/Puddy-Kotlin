package world.puddy.core.global.jwt

data class LoginToken(
    val accessToken: String,
    val refreshToken: String,
)
