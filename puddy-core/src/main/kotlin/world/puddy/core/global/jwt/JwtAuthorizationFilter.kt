package world.puddy.core.global.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import world.puddy.core.global.error.exception.BusinessException
import world.puddy.core.global.auth.JwtUserDetails

@Component
class JwtAuthorizationFilter(
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader(HEADER_STRING)

        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authorizationHeader.replace(TOKEN_PREFIX, "")
        try {
            val context = SecurityContextHolder.createEmptyContext()
            val decodedJWT = JwtVerifier.verify(token)
            val jwtUserDetails = JwtUserDetails(decodedJWT)
            val authentication = UsernamePasswordAuthenticationToken(jwtUserDetails, null, jwtUserDetails.authorities)
            context.authentication = authentication
            SecurityContextHolder.setContext(context)
        } catch (e: SecurityException) {
            setJwtException(request, JwtException.WRONG_TOKEN)
        } catch (e: MalformedJwtException) {
            setJwtException(request, JwtException.WRONG_TOKEN)
        } catch (e: UnsupportedJwtException) {
            setJwtException(request, JwtException.WRONG_TOKEN)
        } catch (e: IllegalArgumentException) {
            setJwtException(request, JwtException.WRONG_TOKEN)
        } catch (e: ExpiredJwtException) {
            setJwtException(request, JwtException.EXPIRED_TOKEN)
        } catch (e: BusinessException) {
            setJwtException(request, JwtException.EXPIRED_TOKEN)
        } catch (e: Exception) {
            setJwtException(request, JwtException.UNKNOWN_ERROR)
        }
        filterChain.doFilter(request, response)
    }

    private fun setJwtException(request: HttpServletRequest, exception: JwtException) {
        request.setAttribute("exception", exception.name)
    }

    companion object {
        private const val TOKEN_PREFIX = "Bearer "
        private const val HEADER_STRING = "Authorization"
    }
}