plugins {
    alias(libs.plugins.spendless.android.library)
    alias(libs.plugins.spendless.jvm.ktor)
}

android {
    namespace = "dev.gaddal.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}