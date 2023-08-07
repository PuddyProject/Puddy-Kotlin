package world.puddy.domain.user.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import world.puddy.core.domain.user.application.port.out.FindUserPort
import world.puddy.core.domain.user.application.port.out.JoinUserPort
import world.puddy.core.domain.user.application.service.UserCommandService
import world.puddy.core.global.error.exception.DuplicateRegisterException
import world.puddy.core.global.jwt.JwtTokenProvider
import world.puddy.domain.user.UserFixture

class UserServiceTest : BehaviorSpec({

    val joinUserPort = mockk<JoinUserPort>()
    val findUserPort = mockk<FindUserPort>()
    val jwtTokenProvider = mockk<JwtTokenProvider>()
    val userService = UserCommandService(joinUserPort, findUserPort, jwtTokenProvider)

    given("회원 가입을 요청하는 경우") {
        `when`("모든 정보가 포함되면") {
            val command = UserFixture.joinUserCommand("test@test.com")
            every { findUserPort.existsByAccount("test") } returns false
            every { joinUserPort.saveUser(any()) } returns 1L
            then("회원 가입이 완료된다") {
                userService.join(command)
                verify(exactly = 1) { findUserPort.existsByAccount("test") }
                verify(exactly = 1) { joinUserPort.saveUser(any()) }

                clearMocks(findUserPort, joinUserPort)
            }
        }
        `when`("이미 가입된 계정이면") {
            val command = UserFixture.joinUserCommand("test@test.com")
            every { findUserPort.existsByAccount("test") } returns true
            then("회원 가입이 실패한다") {
                shouldThrow<DuplicateRegisterException> {
                    userService.join(command)
                }
                verify(exactly = 1) { findUserPort.existsByAccount("test") }
                verify(exactly = 0) { joinUserPort.saveUser(any()) }
            }
        }
    }
})
