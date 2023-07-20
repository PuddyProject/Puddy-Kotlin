package world.puddy.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDate

@Embeddable
data class UserInformation(
    @Column(nullable = false, length = 15, unique = true)
    val account: String,

    @Column(nullable = false, length = 30)
    val username: String,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(nullable = false, length = 13)
    val phoneNumber: String,

    ) {
    fun same(name: String, phoneNumber: String): Boolean {
        return this.username == name && this.phoneNumber == phoneNumber
    }
}
