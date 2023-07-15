package world.puddy.question.adapter.out.persistence

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.question.application.port.out.RegisterQuestionPort
import world.puddy.question.domain.Question

@Service
@Transactional(readOnly = true)
class QuestionPersistenceAdapter(
    private val questionRepository: QuestionRepository,
    private val questionMapper: QuestionMapper
) : RegisterQuestionPort {

    @Transactional
    override fun registerQuestion(command: RegisterQuestionCommand): Question {
        return questionRepository.save(questionMapper.toEntity(command))
    }
}
