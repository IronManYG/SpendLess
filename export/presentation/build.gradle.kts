plugins {
    alias(libs.plugins.spendless.android.feature.ui)
}

android {
    namespace = "dev.gaddal.export.presentation"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.export.domain)
}