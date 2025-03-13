plugins {
    alias(libs.plugins.spendless.android.library)
    alias(libs.plugins.spendless.jvm.ktor)
}

android {
    namespace = "dev.gaddal.transactions.network"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.data)
}