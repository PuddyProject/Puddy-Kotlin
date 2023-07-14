package world.puddy.question.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import world.puddy.question.domain.Question

interface QuestionRepository : JpaRepository<Question, Long>
