package world.puddy.question.adapter.out.persistence

import world.puddy.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.question.application.port.out.RegisterQuestionPort
import world.puddy.question.domain.Question

class QuestionPersistenceAdapter(
    private val questionRepository: QuestionRepository,
) : RegisterQuestionPort {

    override fun registerQuestion(command: RegisterQuestionCommand): Question {
        return questionRepository.save(QuestionMapper.toEntity(command))
    }
}
