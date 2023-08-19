package world.puddy.api.domain.question

import org.springframework.mock.web.MockMultipartFile
import world.puddy.core.domain.image.domain.Image
import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.core.domain.question.domain.Category
import world.puddy.core.domain.question.domain.Question

object QuestionFixture {

    fun registerQuestionCommand(): RegisterQuestionCommand {
        val image1 = MockMultipartFile(
            "file1", // 파일 이름
            "file1.png", // 원본 파일 이름
            "image/png", // 컨텐츠 타입
            byteArrayOf(0) // 파일의 내용, 여기서는 단순 예제로 비어있는 바이트 배열 사용
        )

        val image2 = MockMultipartFile(
            "file2",
            "file2.png",
            "image/png",
            byteArrayOf(0)
        )
        return RegisterQuestionCommand(
            title = "질문글 제목",
            content = "질문글 내용",
            category = Category.건강,
            postCategory = 1,
            images = listOf(image1, image2)
        )
    }

    fun question(): Question {
        return Question(
            1L,
            "질문글 제목",
            "질문글 내용",
            Category.건강,
            1,
            images(),
            1L
        )
    }

    private fun image(id: Long): Image {
        return Image(
            "image.jpg",
            "original.jpg",
            "stored.jpg",
            id
        )
    }

    fun images(): MutableList<Image> {
        return mutableListOf(
            image(1L),
            image(2L),
            image(3L)
        )
    }
}
