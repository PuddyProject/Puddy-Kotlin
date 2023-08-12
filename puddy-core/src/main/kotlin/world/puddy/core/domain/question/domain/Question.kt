package world.puddy.core.domain.question.domain

import jakarta.persistence.*
import world.puddy.core.domain.image.domain.Image
import world.puddy.core.global.error.exception.UnauthorizedException
import world.puddy.core.global.jpa.BaseEntity

@Entity
@Table(name = "question")
class Question(

    @Column(name = "member_id")
    private val memberId: Long,

    @Column(name = "title", length = 50) var title: String,

    @Lob
    @Column(name = "content")
    var content: String,

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    var category: Category,

    val postCategory: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    val id: Long = 0L

) : BaseEntity() {

    @OneToMany
    @JoinColumn(name = "image_id")
    val images: MutableList<Image> = mutableListOf()

    fun edit(title: String, content: String, category: String): Long {
        this.title = title
        this.content = content
        this.category = world.puddy.core.domain.question.domain.Category.valueOf(category)

        return this.id
    }

    fun assertOwnedBy(memberId: Long) {
        if (this.memberId != memberId) {
            throw UnauthorizedException()
        }
    }

    fun addImage(image: Image) {
        this.images.add(image)
    }
}
