package world.puddy.user

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["world.puddy.user"])
@AutoConfiguration
@Configuration
class UserConfigurationLoader
