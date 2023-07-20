package world.puddy.question.adapter.out.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import world.puddy.common.error.exception.QuestionNotFoundException
import world.puddy.question.application.port.`in`.EditQuestionCommand
import world.puddy.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.question.application.port.out.DeleteQuestionPort
import world.puddy.question.application.port.out.EditQuestionPort
import world.puddy.question.application.port.out.FindQuestionListPort
import world.puddy.question.application.port.out.FindQuestionPort
import world.puddy.question.application.port.out.RegisterQuestionPort
import world.puddy.question.domain.Question

@Service
class QuestionPersistenceAdapter(
    private val questionRepository: QuestionRepository,
    private val questionMapper: QuestionMapper
) : RegisterQuestionPort, FindQuestionPort, FindQuestionListPort, EditQuestionPort, DeleteQuestionPort {

    override fun registerQuestion(command: RegisterQuestionCommand): Question {
        return questionRepository.save(questionMapper.toEntity(command))
    }

    override fun findQuestion(id: Long): Question {
        return questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException()
    }

    override fun findQuestionList(): List<Question> = questionRepository.findAll()

    override fun editQuestion(id: Long, command: EditQuestionCommand): Long {
        val question = questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException()
        question.edit(command.title, command.content, command.category)
        return id
    }

    override fun deleteQuestion(id: Long) {
        questionRepository.deleteById(id)
    }
}
