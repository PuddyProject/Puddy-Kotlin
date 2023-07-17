package world.puddy.question.domain.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class CategoryValidator : ConstraintValidator<Category, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return if (value == null) {
            true
        } else {
            try {
                world.puddy.question.domain.Category.valueOf(value)
                true
            } catch (ex: IllegalArgumentException) {
                false
            }
        }
    }
}
