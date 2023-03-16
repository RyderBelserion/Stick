plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.shadow)
    implementation(libs.paperweight)
}