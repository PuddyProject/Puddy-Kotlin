package world.puddy.core

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["world.puddy.core"])
@AutoConfiguration
@Configuration
@EnableCaching
class CoreConfigurationLoader
