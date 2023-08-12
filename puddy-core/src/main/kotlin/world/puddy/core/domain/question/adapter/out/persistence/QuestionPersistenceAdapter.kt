package world.puddy.core.domain.question.adapter.out.persistence

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import world.puddy.core.domain.question.application.port.out.DeleteQuestionPort
import world.puddy.core.domain.question.application.port.out.FindQuestionPort
import world.puddy.core.domain.question.application.port.out.RegisterQuestionPort
import world.puddy.core.domain.question.domain.Question
import world.puddy.core.global.error.exception.QuestionNotFoundException

@Repository
class QuestionPersistenceAdapter(
    private val questionRepository: QuestionRepository,
) : RegisterQuestionPort,
    FindQuestionPort,
    DeleteQuestionPort {

    override fun registerQuestion(question: Question): Long =
        questionRepository.save(question).id

    override fun findQuestion(id: Long): Question {
        return questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException()
    }

    override fun findQuestionList(page: Pageable, keyword: String, sort: String): List<Question> =
        questionRepository.findAll()

    override fun deleteQuestion(id: Long) {
        questionRepository.deleteById(id)
    }
}
