plugins {
    `java-library`
    `maven-publish`

    id("com.github.johnrengelman.shadow")
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
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}