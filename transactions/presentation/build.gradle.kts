plugins {
    alias(libs.plugins.spendless.android.feature.ui)
}

android {
    namespace = "dev.gaddal.transactions.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)
    implementation(libs.lottie)

    implementation(projects.core.domain)
    implementation(projects.transactions.domain)
    implementation(libs.androidx.navigation.compose)
}