package world.puddy.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class QuestionTest : StringSpec({

    "질문 글이 생성된다." {
        shouldNotThrowAny { Question("질문 제목", "질문 내용") }
    }

    "질문 제목은 50자를 넘을 수 없다." {
        shouldThrow<IllegalArgumentException>
        {
            Question("질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목", "질문 내용")
        }.message shouldBe "질문 제목은 50자를 넘을 수 없습니다."
    }

    "질문 제목은 빈칸일 수 없다." {
        shouldThrow<IllegalArgumentException>
        {
            Question("", "질문 내용")
        }.message shouldBe "질문 제목은 빈칸일 수 없습니다."
    }

    "질문 내용은 빈칸일 수 없다." {
        shouldThrow<IllegalArgumentException>
        {
            Question("질문 제목", "")
        }.message shouldBe "질문 내용은 빈칸일 수 없습니다."
    }
})
