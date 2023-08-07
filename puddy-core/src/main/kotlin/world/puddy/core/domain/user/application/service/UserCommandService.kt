package world.puddy.core.domain.user.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import world.puddy.core.domain.user.application.port.`in`.JoinUserCommand
import world.puddy.core.domain.user.application.port.`in`.JoinUserUseCase
import world.puddy.core.domain.user.application.port.`in`.LoginUserCommand
import world.puddy.core.domain.user.application.port.`in`.LoginUserUseCase
import world.puddy.core.domain.user.application.port.out.FindUserPort
import world.puddy.core.domain.user.application.port.out.JoinUserPort
import world.puddy.core.domain.user.domain.Password
import world.puddy.core.global.error.exception.DuplicateRegisterException
import world.puddy.core.global.jwt.CreateTokenCommand
import world.puddy.core.global.jwt.JwtTokenProvider
import world.puddy.core.global.jwt.LoginToken

@Service
class UserCommandService(
    private val joinUserPort: JoinUserPort,
    private val findUserPort: FindUserPort,
    private val jwtTokenProvider: JwtTokenProvider,
) : JoinUserUseCase, LoginUserUseCase {

    @Transactional
    override fun join(command: JoinUserCommand) {
        if (findUserPort.existsByAccount(command.account)) {
            throw DuplicateRegisterException()
        }
        joinUserPort.saveUser(command.toEntity())
    }

    @Transactional
    override fun login(command: LoginUserCommand): LoginToken {
        val user = findUserPort.getUserByAccount(command.account)
            .also { it.authenticate(Password(command.password)) }
        return jwtTokenProvider.createToken(
            CreateTokenCommand.from(
                user.id,
                user.username,
                user.nickname,
                user.account,
                user.email,
                user.role,
            )
        )
    }
}