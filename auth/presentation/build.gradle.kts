plugins {
    alias(libs.plugins.spendless.android.feature.ui)
}

android {
    namespace = "dev.gaddal.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}