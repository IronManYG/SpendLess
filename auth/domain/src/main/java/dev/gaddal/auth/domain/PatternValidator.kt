package dev.gaddal.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}