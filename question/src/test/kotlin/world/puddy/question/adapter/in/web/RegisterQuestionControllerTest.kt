package world.puddy.question.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import world.puddy.question.adapter.out.persistence.QuestionMapper
import world.puddy.question.application.port.`in`.RegisterQuestionCommand
import world.puddy.question.application.port.`in`.RegisterQuestionUseCase
import world.puddy.question.domain.Category
import world.puddy.question.domain.Question

class RegisterQuestionControllerTest : DescribeSpec({
    val registerQuestionUseCase = mockk<RegisterQuestionUseCase>()
    val questionMapper = mockk<QuestionMapper>()
    val controller = RegisterQuestionController(registerQuestionUseCase, questionMapper)
    val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    val objectMapper = ObjectMapper()

    afterContainer {
        clearAllMocks()
    }

    describe("질문 등록을 요청할 때") {
        val request = RegisterQuestionRequest(
            memberId = 1L,
            title = "질문 제목",
            content = "질문 내용",
            category = Category.먹이,
            postCategory = 1
        )
        val requestPart = MockMultipartFile(
            "request",
            "",
            MediaType.APPLICATION_JSON_VALUE,
            objectMapper.writeValueAsString(request)
                .toByteArray()
        )
        context("필수 정보를 모두 입력시") {
            val command = RegisterQuestionCommand(
                memberId = 1L,
                title = "질문 제목",
                content = "질문 내용",
                category = Category.먹이,
                postCategory = 1,
                images = null
            )
            every { questionMapper.toCommand(request, null) } returns command
            every { registerQuestionUseCase.registerQuestion(command) } returns Question(
                id = 1L,
                memberId = 1L,
                title = "질문 제목",
                content = "질문 내용",
                category = Category.먹이,
                postCategory = 1
            )

            mockMvc.perform(
                multipart("/questions")
                    .file(requestPart)
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            it("요청이 성공한다.") {
                verify { questionMapper.toCommand(request, null) }
                verify { registerQuestionUseCase.registerQuestion(command) }
            }
        }

        context("필수 정보와 이미지가 제공될 때") {
            val image = MockMultipartFile(
                "images",
                "test.png",
                MediaType.IMAGE_PNG_VALUE,
                "Test image content".toByteArray()
            )
            val commandWithImages = RegisterQuestionCommand(
                memberId = 1L,
                title = "질문 제목",
                content = "질문 내용",
                category = Category.먹이,
                postCategory = 1,
                images = listOf(image)
            )
            every { questionMapper.toCommand(request, listOf(image)) } returns commandWithImages
            every { registerQuestionUseCase.registerQuestion(commandWithImages) } returns Question(
                id = 1L,
                memberId = 1L,
                title = "질문 제목",
                content = "질문 내용",
                category = Category.먹이,
                postCategory = 1
            )

            mockMvc.perform(
                multipart("/questions")
                    .file(requestPart)
                    .file(image)
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            it("요청이 성공한다.") {
                verify { questionMapper.toCommand(request, listOf(image)) }
                verify { registerQuestionUseCase.registerQuestion(commandWithImages) }
            }
        }
    }
})
