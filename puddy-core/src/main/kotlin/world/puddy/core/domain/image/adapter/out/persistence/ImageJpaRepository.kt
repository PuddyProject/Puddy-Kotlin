package world.puddy.core.domain.image.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import world.puddy.core.domain.image.domain.Image

interface ImageJpaRepository : JpaRepository<Image, Long>
