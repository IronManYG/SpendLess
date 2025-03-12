plugins {
    alias(libs.plugins.spendless.android.library)
}

android {
    namespace = "dev.gaddal.core.database"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
}