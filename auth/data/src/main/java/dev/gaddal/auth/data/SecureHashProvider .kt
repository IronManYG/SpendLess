package dev.gaddal.auth.data

import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory

object SecureHashProvider {

    // Configure Argon2 parameters
    // Adjust these over time (e.g. increase memory/iterations) for stronger security.
    private const val ITERATIONS = 3        // Time cost
    private const val MEMORY = 65536        // Memory cost (in KiB)
    private const val PARALLELISM = 1       // Degree of parallelism

    private val argon2: Argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id)

    /**
     * Hash the given pinCode using Argon2id.
     */
    fun hashPin(pinCode: String): String {
        return argon2.hash(ITERATIONS, MEMORY, PARALLELISM, pinCode.toCharArray())
    }

    /**
     * Verify the given plain-text pinCode against the hashedPin.
     */
    fun verifyPin(hashedPin: String, inputPin: String): Boolean {
        return argon2.verify(hashedPin, inputPin.toCharArray())
    }
}
