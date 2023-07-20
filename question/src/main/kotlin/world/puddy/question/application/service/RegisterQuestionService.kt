package world.puddy.question.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.question.application.port.`in`.RegisterQuestionUseCase
import world.puddy.question.application.port.out.RegisterQuestionPort

@Service
@Transactional
class RegisterQuestionService(
    private val registerQuestionPort: RegisterQuestionPort
) : RegisterQuestionUseCase {

    override fun registerQuestion(command: RegisterQuestionCommand): Long {
        return registerQuestionPort.registerQuestion(command)
    }
}
