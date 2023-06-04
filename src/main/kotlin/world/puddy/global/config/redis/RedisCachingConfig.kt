package world.puddy.global.config.redis

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import java.time.format.DateTimeFormatter


@EnableCaching
@Configuration
class RedisCachingConfig(
    private val redisConnectionFactory: RedisConnectionFactory
) {

    @Bean
    fun redisCacheManager(): CacheManager? {
        val redisCacheConfiguration = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer<String>(StringRedisSerializer()))
            .entryTtl(Duration.ofHours(12))
        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .build()
    }

    @Bean
    fun dateFormatter(): DateTimeFormatter? {
        return DateTimeFormatter.ofPattern("yy.MM.dd")
    }
}