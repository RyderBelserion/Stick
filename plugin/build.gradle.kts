plugins {
    `java-library`

    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "1.5.4"

    id("xyz.jpenilla.run-paper") version "2.0.1"
}

repositories {
    mavenCentral()

    maven("https://jitpack.io")

    maven("https://repo.crazycrew.us/api")

    maven("https://repo.papermc.io/repository/maven-public")
}

dependencies {
    paperweight.paperDevBundle("1.19.4-R0.1-SNAPSHOT")

    implementation("com.ryderbelserion.stick:stick-api:2.1.0.14")

    //compileOnly("dev.folia:folia-api:1.19.4-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of("17"))
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }

    shadowJar {
        exclude("**/META-INF/**")
    }

    runServer {
        minecraftVersion("1.19.4")
    }

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