package world.puddy.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import world.puddy.common.jpa.BaseEntity

@Entity
@Table(name = "member")
class Member(

    @Column(name = "username")
    private val username: String,

    @Column(name = "nickname")
    private val nickname: String,

    @Column(name = "email", unique = true)
    private val email: String,

    @Column(name = "password")
    private val password: String,

    @Column(name = "account", unique = true)
    private val account: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0L,
) : BaseEntity()
