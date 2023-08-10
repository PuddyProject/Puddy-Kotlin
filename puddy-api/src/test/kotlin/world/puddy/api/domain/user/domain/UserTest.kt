package world.puddy.api.domain.user.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import world.puddy.core.domain.user.domain.Password
import world.puddy.core.domain.user.domain.User

class UserTest : StringSpec({

    "유저가 생성된다" {
        // given
        val user = User(
            username = "test",
            account = "test",
            password = Password("test"),
            email = "test@test.com",
            notificated = true,
        )
        // when & then
        user shouldNotBe null
        user.username shouldBe "test"
        user.nickname shouldBe "퍼디1234"
        user.account shouldBe "test"
    }
})
