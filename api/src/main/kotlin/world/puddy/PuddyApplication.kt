package world.puddy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import world.puddy.question.QuestionConfigurationLoader
import world.puddy.user.UserConfigurationLoader

@SpringBootApplication
@Import(QuestionConfigurationLoader::class, UserConfigurationLoader::class)
class PuddyApplication

fun main(args: Array<String>) {
    runApplication<PuddyApplication>(*args)
}
