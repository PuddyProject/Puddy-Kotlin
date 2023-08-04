package world.puddy.core.domain.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.core.domain.question.application.port.`in`.RegisterQuestionUseCase
import world.puddy.core.domain.question.application.port.out.RegisterQuestionPort

@Service
@Transactional
class RegisterQuestionService(
    private val registerQuestionPort: RegisterQuestionPort
) : world.puddy.core.domain.question.application.port.`in`.RegisterQuestionUseCase {

    override fun registerQuestion(command: RegisterQuestionCommand): Long {
        return registerQuestionPort.registerQuestion(command)
    }
}
