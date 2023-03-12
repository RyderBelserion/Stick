plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()

    maven("https://jitpack.io")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of("17"))
}

tasks {
    compileJava {
        options.release.set(17)
    }
}