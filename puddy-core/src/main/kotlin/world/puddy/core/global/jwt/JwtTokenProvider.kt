package world.puddy.core.global.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import world.puddy.user.domain.User
import java.util.Date

@Component
class JwtTokenProvider {

    @Value("\${jwt.secret-key}")
    private lateinit var SECRET_KEY: String

    fun createToken(user: User): LoginToken {
        val refreshToken = refreshToken()
        val accessToken = accessToken(user)
        return LoginToken(accessToken, refreshToken)
    }

    protected fun accessToken(user: User): String =
        JWT.create()
            .withSubject(user.account)
            .withExpiresAt(Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
            .withClaim("id", user.id)
            .withClaim("username", user.username)
            .withClaim("nickname", user.nickname)
            .withClaim("email", user.email)
            // .withClaim("auth", user.getRole()) TODO: 권한 추가
            // .withClaim("provider",user.getProvider().name()) TODO: 소셜 로그인 추가
            .sign(Algorithm.HMAC256(SECRET_KEY))

    protected fun refreshToken(): String =
        JWT.create()
            .withExpiresAt(Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
            .sign(Algorithm.HMAC256(SECRET_KEY))

    companion object {
        const val ACCESS_EXPIRATION_TIME = 120 * 60 * 1000L // 2시간

        const val REFRESH_EXPIRATION_TIME = 14 * 60 * 60 * 24 * 1000L // 14일
    }
}
