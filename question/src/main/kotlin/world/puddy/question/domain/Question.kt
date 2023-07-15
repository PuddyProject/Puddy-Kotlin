package world.puddy.question.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private val id: Long = 0L
) : BaseEntity()
