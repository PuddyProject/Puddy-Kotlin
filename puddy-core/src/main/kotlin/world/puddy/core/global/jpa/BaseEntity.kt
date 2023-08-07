package world.puddy.core.global.jpa

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    var modifiedDate: LocalDateTime = LocalDateTime.now()
}
