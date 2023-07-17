package world.puddy.question.domain.validator

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CategoryValidator::class])
annotation class Category(

    val groups: Array<KClass<*>> = [],

    val payload: Array<KClass<out Any>> = []
)
