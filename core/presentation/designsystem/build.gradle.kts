plugins {
    alias(libs.plugins.spendless.android.library.compose)
}

android {
    namespace = "dev.gaddal.core.presentation.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.lottie)
    debugImplementation(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.material3)
}