plugins {
    `java-library`
    `maven-publish`

    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()

    maven("https://jitpack.io")
}

dependencies {
    implementation("com.zaxxer:HikariCP:5.0.1")

    //compileOnly("com.google.code.gson:gson:2.10.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of("17"))
}

tasks {
    compileJava {
        options.release.set(17)
    }

    shadowJar {
        listOf(
            "com.zaxxer"
        ).forEach { pack -> relocate(pack, "${rootProject.group}.$pack") }
    }
}