package world.puddy.domain.user

import jakarta.persistence.*
import world.puddy.domain.BaseEntity
import world.puddy.domain.question.Question

@Entity
@Table(name = "\"user\"")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long? = null,

    @Column(name = "account", nullable = false, unique = true)
    val account: String,

    @Column(name = "email", nullable = false, unique = true)
    var email: String,

    var password: String,

    var nickname: String,

    val username: String,

    var role: String,

    var isNotificated: Boolean = false,

    ) : BaseEntity() {

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var questionList: MutableList<Question> = mutableListOf()

//    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
//    var answerList: MutableList<Answer> = mutableListOf()

    fun updateAuth() {
        this.role = UserRole.EXPERT.role
    }

}