package world.puddy.core.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import world.puddy.core.global.jwt.JwtAuthenticationEntryPoint
import world.puddy.core.global.jwt.JwtAuthorizationFilter

@Configuration
class SecurityConfig(
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAuthorizationFilter: JwtAuthorizationFilter,
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http.csrf { it.disable() }
        .sessionManagement { it.disable() }
        .authorizeHttpRequests {
            it.requestMatchers(HttpMethod.POST, "/questions/**").hasRole("USER")
                .requestMatchers("/users/**").permitAll()
                .anyRequest().permitAll()
        }
        .httpBasic { it.disable() }
        .formLogin { it.disable() }
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter::class.java)
        .exceptionHandling { it.authenticationEntryPoint(jwtAuthenticationEntryPoint) }
        .build()
}
