package world.puddy.question.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import world.puddy.common.response.Response
import world.puddy.question.QuestionSnippets
import world.puddy.question.application.port.`in`.EditQuestionUseCase

class EditQuestionControllerTest : DescribeSpec({
    val editQuestionUseCase = mockk<EditQuestionUseCase>()
    val editQuestionController = EditQuestionController(editQuestionUseCase)
    val mockMvc = MockMvcBuilders.standaloneSetup(editQuestionController).build()
    val objectMapper = ObjectMapper().registerModule(JavaTimeModule())

    afterContainer {
        clearAllMocks()
    }
    describe("질문 글을 수정할 때") {
        val registeredId = 1L
        context("필수 정보를 모두 입력하면") {
            val request = QuestionSnippets.editQuestionCommand
            val requestPart = MockMultipartFile(
                "request",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(request)
                    .toByteArray()
            )

            val image = MockMultipartFile(
                "images",
                "test.png",
                MediaType.IMAGE_PNG_VALUE,
                "Test image content".toByteArray()
            )
            every { editQuestionUseCase.editQuestion(registeredId, request) } returns registeredId
            it("질문 글이 수정된다.") {
                val expectedResponse = objectMapper.writeValueAsString(Response.ok(registeredId))

                mockMvc.perform(
                    multipart("/questions/$registeredId")
                        .file(requestPart)
                        .file(image)
                        .with { request ->
                            request.method = "PUT"
                            request
                        }
                )
                    .andExpect(status().isOk)
                    .andExpect(content().json(expectedResponse))
            }
        }
    }
})
