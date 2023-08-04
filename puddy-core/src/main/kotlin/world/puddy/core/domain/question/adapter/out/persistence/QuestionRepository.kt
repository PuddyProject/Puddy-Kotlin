package world.puddy.core.domain.question.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import world.puddy.core.domain.question.domain.Question

interface QuestionRepository : JpaRepository<world.puddy.core.domain.question.domain.Question, Long>
