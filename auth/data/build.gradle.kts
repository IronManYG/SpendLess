plugins {
    alias(libs.plugins.spendless.android.library)
    alias(libs.plugins.spendless.jvm.ktor)
}

android {
    namespace = "dev.gaddal.auth.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}