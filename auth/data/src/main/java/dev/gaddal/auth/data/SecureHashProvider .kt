package dev.gaddal.auth.data

import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object SecureHashProvider {
    private const val ALGORITHM = "PBKDF2WithHmacSHA256"
    private const val ITERATIONS = 120000
    private const val KEY_LENGTH = 256
    private const val SALT_LENGTH = 16

    /**
     * Hash the given pinCode using PBKDF2 with a random salt.
     * Returns a string in the format "salt:hash" encoded in Base64.
     */
    suspend fun hashPin(pinCode: String): String = withContext(Dispatchers.Default) {
        val salt = ByteArray(SALT_LENGTH)
        SecureRandom().nextBytes(salt)

        val hash = generateHash(pinCode, salt)

        val saltBase64 = Base64.encodeToString(salt, Base64.NO_WRAP)
        val hashBase64 = Base64.encodeToString(hash, Base64.NO_WRAP)

        "$saltBase64:$hashBase64"
    }

    /**
     * Verify the given plain-text pinCode against the hashedPin.
     */
    suspend fun verifyPin(hashedPin: String, inputPin: String): Boolean =
        withContext(Dispatchers.Default) {
            val parts = hashedPin.split(":")
            if (parts.size != 2) return@withContext false

            val salt = Base64.decode(parts[0], Base64.NO_WRAP)
            val expectedHash = Base64.decode(parts[1], Base64.NO_WRAP)

            val actualHash = generateHash(inputPin, salt)

            expectedHash.contentEquals(actualHash)
        }

    private fun generateHash(pinCode: String, salt: ByteArray): ByteArray {
        val spec = PBEKeySpec(pinCode.toCharArray(), salt, ITERATIONS, KEY_LENGTH)
        val factory = SecretKeyFactory.getInstance(ALGORITHM)
        return factory.generateSecret(spec).encoded
    }
}