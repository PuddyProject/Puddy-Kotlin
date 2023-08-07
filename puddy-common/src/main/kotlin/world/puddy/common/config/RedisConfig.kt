package world.puddy.common.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.lettuce.core.ClientOptions
import io.lettuce.core.ReadFrom.REPLICA_PREFERRED
import io.lettuce.core.SocketOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import java.time.temporal.ChronoUnit

@Configuration
class RedisConfig {

    @Value("\${spring.data.redis.host}")
    private lateinit var host: String

    @Value("\${spring.data.redis.port}")
    private lateinit var portNumber: String

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
                hostName = host
                port = portNumber.toInt()
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
                .readFrom(REPLICA_PREFERRED)

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
