plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("io.papermc.paperweight:paperweight-userdev:1.5.3")
    implementation("com.github.johnrengelman:shadow:8.1.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20-RC")
}