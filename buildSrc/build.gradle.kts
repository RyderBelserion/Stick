plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()

    maven("https://repo.papermc.io/repository/maven-public/")

    maven("https://repo.crazycrew.us/first-party/")
}

dependencies {
    implementation("io.papermc.paperweight", "paperweight-userdev", "1.5.6-SNAPSHOT")

    implementation("com.github.johnrengelman", "shadow", "8.1.1")
}