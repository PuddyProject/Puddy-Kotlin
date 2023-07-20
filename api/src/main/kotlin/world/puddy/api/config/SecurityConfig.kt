package world.puddy.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http.csrf { it.disable() }
        .sessionManagement { it.disable() }
        .authorizeHttpRequests {
            it.requestMatchers("/questions/**").permitAll()
                .requestMatchers("/users/**").permitAll()
                .anyRequest().permitAll()
        }
        .httpBasic { it.disable() }
        .formLogin { it.disable() }
        .build()
}
