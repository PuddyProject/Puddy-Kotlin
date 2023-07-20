package world.puddy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import world.puddy.question.QuestionConfigurationLoader
import world.puddy.user.UserConfigurationLoader

@SpringBootApplication
@EnableWebSecurity
@Import(QuestionConfigurationLoader::class, UserConfigurationLoader::class)
class PuddyApplication

fun main(args: Array<String>) {
    runApplication<PuddyApplication>(*args)
}
