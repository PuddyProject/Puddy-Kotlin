package world.puddy.core.global.config

import io.lettuce.core.RedisURI
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import world.puddy.core.domain.user.domain.User

@Configuration
@EnableRedisRepositories
class RedisConfig(
    private val redisProperties: RedisProperties
) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redisURI = RedisURI.create(redisProperties.url)
        val configuration = LettuceConnectionFactory.createRedisConfiguration(redisURI)
        val factory = LettuceConnectionFactory(configuration)
        factory.afterPropertiesSet()
        return factory
    }

    @Bean
    fun userRedisTemplate(): RedisTemplate<String, User> {
        val redisTemplate = RedisTemplate<String, User>()
        redisTemplate.connectionFactory = redisConnectionFactory()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(User::class.java)
        return redisTemplate
    }
}
