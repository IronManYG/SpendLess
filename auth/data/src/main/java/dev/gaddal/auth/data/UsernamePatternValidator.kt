package dev.gaddal.auth.data

import dev.gaddal.auth.domain.PatternValidator

object UsernamePatternValidator : PatternValidator {

    // Regular expression to allow letters, digits, underscores;
    // requires length 3–15
    private val USERNAME_REGEX = "^[A-Za-z0-9_]{3,15}$".toRegex()

    override fun matches(value: String): Boolean {
        return value.matches(USERNAME_REGEX)
    }
}
