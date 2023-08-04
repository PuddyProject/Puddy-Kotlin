package world.puddy.core.global.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import world.puddy.common.error.exception.TokenVerifyException

@Component
object JwtVerifier {

    @Value("\${jwt.secret-key}")
    private lateinit var SECRET_KEY: String

    fun verify(token: String): DecodedJWT {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
        } catch (e: Exception) {
            throw TokenVerifyException()
        }
    }

    fun verifyRefreshToken(account: String, refreshToken: String) {

    }

    fun expireRefreshToken(account: String) {
        // redisTemplate.delete(account)
    }

    fun parseAccount(accessToken: String): String {
        return JWT.decode(accessToken).subject
    }
}
