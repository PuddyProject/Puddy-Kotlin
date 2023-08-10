package world.puddy.core.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
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
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http.csrf { it.disable() }
        .sessionManagement { it.disable() }
        .authorizeHttpRequests {
            it.requestMatchers(HttpMethod.POST, "/questions/**").hasAnyRole("USER", "EXPERT")
                .requestMatchers(HttpMethod.PUT, "/questions/**").hasAnyRole("USER", "EXPERT")
                .requestMatchers(HttpMethod.DELETE, "/questions/**").hasAnyRole("USER", "EXPERT")
                .requestMatchers("/users/**").permitAll()
                .anyRequest().permitAll()
        }
        .httpBasic { it.disable() }
        .formLogin { it.disable() }
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter::class.java)
        .exceptionHandling { it.authenticationEntryPoint(jwtAuthenticationEntryPoint) }
        .build()
}
