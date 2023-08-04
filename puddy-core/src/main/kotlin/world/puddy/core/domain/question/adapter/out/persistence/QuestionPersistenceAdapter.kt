package world.puddy.core.domain.question.adapter.out.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import world.puddy.common.error.exception.QuestionNotFoundException
import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.core.domain.question.application.port.out.DeleteQuestionPort
import world.puddy.core.domain.question.application.port.out.EditQuestionPort
import world.puddy.core.domain.question.application.port.out.FindQuestionListPort
import world.puddy.core.domain.question.application.port.out.FindQuestionPort
import world.puddy.core.domain.question.application.port.out.RegisterQuestionPort
@Service
class QuestionPersistenceAdapter(
    private val questionRepository: QuestionRepository,
) : RegisterQuestionPort,
    FindQuestionPort,
    FindQuestionListPort,
    EditQuestionPort,
    DeleteQuestionPort {

    override fun registerQuestion(command: RegisterQuestionCommand): Long {
        // TODO
        return 1L
    }

    override fun findQuestion(id: Long): world.puddy.core.domain.question.domain.Question {
        return questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException()
    }

    override fun findQuestionList(): List<world.puddy.core.domain.question.domain.Question> =
        questionRepository.findAll()

    override fun editQuestion(
        id: Long,
        command: world.puddy.core.domain.question.application.port.`in`.EditQuestionCommand
    ): Long {
        val question = questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException()
        question.edit(command.title, command.content, command.category)
        return id
    }

    override fun deleteQuestion(id: Long) {
        questionRepository.deleteById(id)
    }
}
