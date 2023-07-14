package world.puddy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import world.puddy.question.QuestionConfigurationLoader

@SpringBootApplication
@Import(QuestionConfigurationLoader::class)
class PuddyApplication

fun main(args: Array<String>) {
    runApplication<PuddyApplication>(*args)
}
