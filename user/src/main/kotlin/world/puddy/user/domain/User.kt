package world.puddy.user.domain

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import world.puddy.common.error.ErrorCode
import world.puddy.common.error.exception.UnidentifiedUserException
import world.puddy.common.jpa.BaseEntity

@Entity
@Table(name = "users")
class User(

    @Embedded
    var information: UserInformation,

    var nickname: String = "퍼디1234",

    @AttributeOverride(name = "value", column = Column(name = "password", nullable = false))
    @Embedded
    var password: Password,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) : BaseEntity() {
    val username: String
        get() = information.username

    val email: String
        get() = information.email

    constructor(
        account: String,
        username: String,
        email: String,
        nickname: String,
        password: Password,
        id: Long = 0L
    ) : this(
        UserInformation(account, username, email), nickname, password, id
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
