package world.puddy.common.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@EnableCaching
@Configuration
class CacheConfig {
    /**
     * Redis Cache를 사용하기 위한 cache manager 등록.<br>
     * 커스텀 설정을 적용하기 위해 RedisCacheConfiguration을 먼저 생성한다.<br>
     * 이후 RadisCacheManager를 생성할 때 cacheDefaults의 인자로 configuration을 주면 해당 설정이 적용된다.<br>
     * RedisCacheConfiguration 설정<br>
     * disableCachingNullValues - null값이 캐싱될 수 없도록 설정한다. null값 캐싱이 시도될 경우 에러를 발생시킨다.<br>
     * entryTtl - 캐시의 TTL(Time To Live)를 설정한다. Duraction class로 설정할 수 있다.<br>
     * serializeKeysWith - 캐시 Key를 직렬화-역직렬화 하는데 사용하는 Pair를 지정한다.<br>
     * serializeValuesWith - 캐시 Value를 직렬화-역직렬화 하는데 사용하는 Pair를 지정한다. -> 가시성이 중요하지 않기 때문에 JdkSerializationRedisSerializer 사용<br>
     * Value는 다양한 자료구조가 올 수 있기 때문에 GenericJackson2JsonRedisSerializer를 사용한다.
     *
     * @param redisConnectionFactory Redis와의 연결을 담당한다.
     * @return
     */
    @Bean
    fun redisCacheManager(
        redisConnectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): RedisCacheManager {
        val configuration = RedisCacheConfiguration
            .defaultCacheConfig()
            .disableCachingNullValues()
            .entryTtl(Duration.ofSeconds(DEFAULT_EXPIRE_SECOND.toLong()))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair
                    .fromSerializer(StringRedisSerializer())
            )

        val cacheConfigurations: HashMap<String, RedisCacheConfiguration> = hashMapOf(
            ARTICLES_CACHE_NAME to RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(LIST_EXPIRE_SECOND.toLong()))
                .serializeValuesWith(
                    RedisSerializationContext.SerializationPair
                        .fromSerializer(StringRedisSerializer())
                ),
            QUESTIONS_CACHE_NAME to RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(LIST_EXPIRE_SECOND.toLong()))
                .serializeKeysWith(
                    RedisSerializationContext.SerializationPair
                        .fromSerializer(StringRedisSerializer())
                )
        )

        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory)
            .cacheDefaults(configuration)
            .withInitialCacheConfigurations(cacheConfigurations)
            .build()
    }

    companion object {
        private const val DEFAULT_EXPIRE_SECOND = 60 * 5
        private const val ARTICLES_CACHE_NAME = "articles"
        private const val QUESTIONS_CACHE_NAME = "questions"
        private const val LIST_EXPIRE_SECOND = 5
    }
}
