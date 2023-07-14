package world.puddy.question

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["world.puddy.question"])
@AutoConfiguration
@Configuration
class QuestionConfigurationLoader
