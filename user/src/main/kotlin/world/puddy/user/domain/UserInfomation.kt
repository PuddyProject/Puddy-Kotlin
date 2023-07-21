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
    ) {
    fun same(name: String, email: String): Boolean {
        return this.username == name && email == this.email
    }
}
