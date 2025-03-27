package dev.gaddal.auth.domain

class UserDataValidator(
    private val patternValidator: PatternValidator
) {

    fun isValidUsername(username: String): Boolean {
        return patternValidator.matches(username.trim())
    }
}