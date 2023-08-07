package world.puddy.core.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.lettuce.core.ClientOptions
import io.lettuce.core.ReadFrom
import io.lettuce.core.SocketOptions
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import world.puddy.core.domain.user.domain.User
import java.time.Duration
import java.time.temporal.ChronoUnit

@Configuration
class RedisConfig(
    private val redisProperties: RedisProperties
) {

    @Bean
    fun userRedisTemplate(redisStandaloneConfiguration: RedisStandaloneConfiguration): RedisTemplate<String, User> {
        val redisTemplate = RedisTemplate<String, User>()
        redisTemplate.connectionFactory = redisConnectionFactory(redisStandaloneConfiguration)
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(User::class.java)
        return redisTemplate
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        mapper.registerModules(JavaTimeModule(), Jdk8Module())
        return mapper
    }

    @Bean
    fun redisStandaloneConfiguration(): RedisStandaloneConfiguration {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
            .apply {
                hostName = redisProperties.host
                port = redisProperties.port
            }
        return redisStandaloneConfiguration
    }

    @Bean
    fun redisConnectionFactory(redisStandaloneConfiguration: RedisStandaloneConfiguration): RedisConnectionFactory {
        val socketOptions = SocketOptions.builder().connectTimeout(Duration.of(10, ChronoUnit.MINUTES)).build()

        val clientOptions = ClientOptions.builder().socketOptions(socketOptions).autoReconnect(true).build()
        val clientConfig =
            LettuceClientConfiguration.builder()
                .clientOptions(clientOptions)
                .readFrom(ReadFrom.REPLICA_PREFERRED)

        return LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig.build())
    }

    @Bean
    fun redisTemplate(objectMapper: ObjectMapper): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory(redisStandaloneConfiguration())

        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.setEnableTransactionSupport(true) // transaction 허용

        return redisTemplate
    }
}
