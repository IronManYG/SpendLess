package dev.gaddal.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.gaddal.auth.presentation.register.RegisterRoot
import dev.gaddal.core.presentation.designsystem.SpendLessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
                RegisterRoot(
                    onSuccessfulRegistration = { },
                    onAlreadyHaveAnAccountClick = {}
                )
            }
        }
    }
}