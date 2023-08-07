package world.puddy.core.domain.user.adapter.out.persistence

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class UserPersistenceAdapter(
    private val userRepository: UserRepository
)
