package world.puddy.core.jwt

data class LoginToken(
    val accessToken: String,
    val refreshToken: String,
)
