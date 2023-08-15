package world.puddy.core.domain.question.adapter.out.persistence

import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import world.puddy.core.domain.question.application.port.out.DeleteQuestionPort
import world.puddy.core.domain.question.application.port.out.FindQuestionPort
import world.puddy.core.domain.question.application.port.out.RegisterQuestionPort
import world.puddy.core.domain.question.domain.Question
import world.puddy.core.global.error.exception.QuestionNotFoundException

@Repository
class QuestionPersistenceAdapter(
    private val questionRepository: QuestionRepository,
    private val queryFactory: SpringDataQueryFactory
) : RegisterQuestionPort,
    FindQuestionPort,
    DeleteQuestionPort {

    override fun registerQuestion(question: Question): Question =
        questionRepository.save(question)

    override fun findQuestion(id: Long): Question {
        return questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException()
    }

    override fun findQuestionList(page: Pageable, keyword: String, sort: String): Slice<Question> {
        val questionList = queryFactory.listQuery<Question> {
            select(entity(Question::class))
            from(entity(Question::class))
            where(column(Question::title).like("%$keyword%"))
            limit(page.pageNumber + 1)
            offset(page.offset.toInt())
        }
        return SliceImpl(questionList, page, questionList.size > page.pageNumber)
    }

    override fun deleteQuestion(id: Long) {
        questionRepository.deleteById(id)
    }
}
