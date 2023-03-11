plugins {
    id("crazycore.root-plugin")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of("17"))
}

tasks {
    compileJava {
        options.release.set(17)
    }
}