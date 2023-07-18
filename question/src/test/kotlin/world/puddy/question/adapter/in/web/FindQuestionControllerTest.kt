package world.puddy.question.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import world.puddy.question.application.port.`in`.FindQuestionUseCase
import world.puddy.question.domain.Category
import java.time.LocalDateTime

class FindQuestionControllerTest : DescribeSpec({
    val findQuestionUseCase = mockk<FindQuestionUseCase>()
    val controller = FindQuestionController(findQuestionUseCase)
    val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    val objectMapper = ObjectMapper().registerModule(JavaTimeModule())

    afterContainer {
        clearAllMocks()
    }

    describe("질문글을 조회할 때") {
        val target = FindQuestionResponse(
            id = 1L,
            title = "질문 제목",
            content = "질문 내용",
            category = Category.먹이.name,
            postCategory = 1,
            createdDate = LocalDateTime.of(2023, 7, 17, 14, 38, 42)
        )

        context("id값이 존재하는 질문일 경우") {
            val id = 1L
            val expectedResponse = mapOf(
                "code" to 200,
                "status" to "OK",
                "message" to "OK",
                "data" to target
            )
            every { findQuestionUseCase.findQuestion(id) } returns target
            it("해당 id에 대한 질문글을 반환한다.") {
                mockMvc.perform(
                    get("/questions/$id")
                )
                    .andExpect(status().isOk)
                    .andExpect(
                        content().json(
                            objectMapper.writeValueAsString(
                                expectedResponse
                            )
                        )
                    )
            }
        }
    }
})
