package world.puddy.common.image.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import world.puddy.common.jpa.BaseEntity

@Entity
@Table(name = "image")
class Image(

    @Id
    @GeneratedValue
    private val id: Long = 0L
) : BaseEntity()
