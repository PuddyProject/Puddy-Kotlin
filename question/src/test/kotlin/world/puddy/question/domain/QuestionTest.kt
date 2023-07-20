package world.puddy.question.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import world.puddy.question.QuestionSnippets

class QuestionTest : StringSpec({

    "질문 글이 생성된다." {
        shouldNotThrowAny { Question(1L, "질문 제목", "질문 내용", Category.산책, 1) }
    }

    "질문 제목은 50자를 넘을 수 없다." {
        shouldThrow<IllegalArgumentException>
        {
            Question(
                1L,
                "질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목질문 제목",
                "질문 내용",
                Category.산책,
                1
            )
        }.message shouldBe "질문 제목은 50자를 넘을 수 없습니다."
    }

    "질문 제목은 빈칸일 수 없다." {
        shouldThrow<IllegalArgumentException>
        {
            Question(1L, "", "질문 내용", Category.산책, 1)
        }.message shouldBe "질문 제목은 빈칸일 수 없습니다."
    }

    "질문 내용은 빈칸일 수 없다." {
        shouldThrow<IllegalArgumentException>
        {
            Question(1L, "질문 제목", "", Category.산책, 1)
        }.message shouldBe "질문 내용은 빈칸일 수 없습니다."
    }

    "질문글이 수정된다" {
        val question = QuestionSnippets.question
        question.edit("수정된 제목", "수정된 내용", "기타")
        question.title shouldBe "수정된 제목"
        question.content shouldBe "수정된 내용"
        question.category shouldBe Category.기타
    }
})
