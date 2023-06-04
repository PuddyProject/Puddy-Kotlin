package world.puddy.domain.question

import jakarta.persistence.*
import world.puddy.domain.BaseEntity
import world.puddy.domain.image.domain.Image
import world.puddy.domain.user.User

@Entity
@Table(name = "question")
class Question(

    var title: String,
    var content: String,

    var viewCount: Int = 0,

    var isSolved: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Enumerated(EnumType.STRING)
    var category: Category,

    @Column(name = "post_category")
    val postCategory: Int,


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

//    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    var answerList: MutableList<Answer> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "question_id")
    var imageList: MutableList<Image> = mutableListOf()

) : BaseEntity() {


}