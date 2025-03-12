plugins {
    `kotlin-dsl`
}

group = "dev.gaddal.spendless.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "spendless.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "spendless.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
}