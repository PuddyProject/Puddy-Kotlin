package world.puddy.question.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import world.puddy.common.jpa.BaseEntity

@Entity
@Table(name = "question")
class Question(

    @Column(name = "member_id")
    private val memberId: Long,

    @Column(name = "title", length = 50)
    private val title: String,

    @Lob
    @Column(name = "content")
    private val content: String,

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private val category: Category,

    private val postCategory: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private val id: Long = 0L
) : BaseEntity() {
    init {
        require(title.length <= 50) { "질문 제목은 50자를 넘을 수 없습니다." }
        require(title.isNotBlank()) { "질문 제목은 빈칸일 수 없습니다."}
        require(content.isNotBlank()) { "질문 내용은 빈칸일 수 없습니다." }
    }
}
