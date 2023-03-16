plugins {
    `java-library`
    `maven-publish`

    id("com.github.johnrengelman.shadow")
    kotlin("jvm")
}

repositories {
    mavenCentral()

    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of("17"))
}

kotlin {
    jvmToolchain(17)
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(17)
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
            javaParameters = true
        }
    }
}