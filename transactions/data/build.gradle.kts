plugins {
    alias(libs.plugins.spendless.android.library)
}

android {
    namespace = "dev.gaddal.transactions.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.work)
    implementation(libs.koin.android.workmanager)
    implementation(libs.kotlinx.serialization.json)

    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.transactions.domain)
}