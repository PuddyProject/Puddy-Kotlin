package world.puddy.member.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec

class MemberTest : StringSpec({

    "회원이 생성된다." {
        shouldNotThrowAny { Member("username", "nickname", "email", "password", "account") }
    }
})
