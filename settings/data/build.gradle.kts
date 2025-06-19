plugins {
    alias(libs.plugins.spendless.android.library)
}

android {
    namespace = "dev.gaddal.settings.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.datastore)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.settings.domain)
}