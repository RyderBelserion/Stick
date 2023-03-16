plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.kotlin.serialization)

    implementation(libs.shadow)
    implementation(libs.paperweight)
}