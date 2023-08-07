package world.puddy.core.domain.question.adapter.out.persistence

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import world.puddy.core.global.error.exception.QuestionNotFoundException
import world.puddy.core.domain.question.application.port.`in`.EditQuestionCommand
import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.core.domain.question.application.port.out.*
import world.puddy.core.domain.question.domain.Question

@Repository
class QuestionPersistenceAdapter(
    private val questionRepository: QuestionRepository,
) : RegisterQuestionPort,
    FindQuestionPort,
    FindQuestionListPort,
    EditQuestionPort,
    DeleteQuestionPort {

    override fun registerQuestion(command: RegisterQuestionCommand): Long =
        questionRepository.save(command.toEntity()).id

    override fun findQuestion(id: Long): Question {
        return questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException()
    }

    override fun findQuestionList(page: Pageable, keyword: String, sort: String): List<Question> =
        questionRepository.findAll()

    override fun editQuestion(
        command: EditQuestionCommand
    ): Long {
        val question = questionRepository.findByIdOrNull(command.questionId) ?: throw QuestionNotFoundException()
        question.verify(command.memberId)
        return question.edit(command.title, command.content, command.category)
    }

    override fun deleteQuestion(id: Long) {
        questionRepository.deleteById(id)
    }
}
