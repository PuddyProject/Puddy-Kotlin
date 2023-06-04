package world.puddy.domain.image.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import world.puddy.domain.BaseEntity

@Entity
class Image(

    val savedName: String,

    val originalName: String,

    val imagePath: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : BaseEntity()