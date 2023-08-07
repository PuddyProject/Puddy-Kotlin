package world.puddy.core.domain.user.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import world.puddy.core.global.error.ErrorCode
import world.puddy.core.global.error.exception.UnidentifiedUserException
import world.puddy.core.global.jpa.BaseEntity

@Entity
@Table(name = "users")
class User(

    @Embedded
    var information: UserInformation,

    @AttributeOverride(name = "value", column = Column(name = "password", nullable = false))
    @Embedded
    var password: Password,

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: UserRole,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) : BaseEntity() {

    val account: String
        get() = information.account
    val username: String
        get() = information.username

    val email: String
        get() = information.email

    var nickname: String = "퍼디1234"

    constructor(
        account: String,
        username: String,
        email: String,
        notificated: Boolean,
        password: Password,
        id: Long = 0L
    ) : this(
        UserInformation(account, username, notificated, email), password, UserRole.ROLE_USER, id
    )

    fun authenticate(password: Password) {
        identify(this.password == password) { "사용자 정보가 일치하지 않습니다." }
    }

    fun changePassword(oldPassword: Password, newPassword: Password) {
        identify(this.password == oldPassword) { "기존 비밀번호가 일치하지 않습니다." }
        this.password = newPassword
    }

    private fun identify(value: Boolean, lazyMessage: () -> Any = {}) {
        if (!value) {
            val message = lazyMessage()
            throw UnidentifiedUserException(ErrorCode.valueOf(message.toString()))
        }
    }
}
