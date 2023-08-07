package world.puddy.core.domain.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class UserInformation(
    @Column(nullable = false, length = 15, unique = true)
    val account: String,

    @Column(nullable = false, length = 30)
    val username: String,

    var notificated: Boolean = false,

    @Column(unique = true, nullable = false)
    val email: String,
) {
    fun same(account: String, email: String): Boolean {
        return this.account == account && email == this.email
    }
}
