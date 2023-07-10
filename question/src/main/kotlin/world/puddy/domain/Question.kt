package world.puddy.domain

class Question(
    private val title: String,
    private val content: String
) {
    init {
        require(title.length <= 50) { "질문 제목은 50자를 넘을 수 없습니다." }
        require(title.isNotBlank()) { "질문 제목은 빈칸일 수 없습니다." }
    }
}
