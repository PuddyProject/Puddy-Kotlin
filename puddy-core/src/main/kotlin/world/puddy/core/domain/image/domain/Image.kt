package world.puddy.core.domain.image.domain

import jakarta.persistence.*
import world.puddy.core.global.jpa.BaseEntity

@Entity
class Image(

    /**
     * S3에 이미지가 저장된, 접근할 수 있는 경로
     */
    private val imagePath: String,

    /**
     * 원본 파일 이름
     */
    private val originalName: String,

    /**
     * DB에 저장되는 파일 이름은 originalName + UUID로 설정한다.
     */
    private val storedName: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0L
) : BaseEntity()
