package world.puddy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["world.puddy.core", "world.puddy.api"]
)
class PuddyApplication

fun main(args: Array<String>) {
    runApplication<PuddyApplication>(*args)
}
