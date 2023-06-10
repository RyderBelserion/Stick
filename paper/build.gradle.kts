plugins {
    `java-library`
    `maven-publish`

    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "1.5.5"
}

val isSnapshot = rootProject.version.toString().contains("snapshot")

repositories {
    maven("https://repo.papermc.io/repository/maven-public")

    maven("https://jitpack.io")

    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle("1.20-R0.1-SNAPSHOT")

    compileOnly("com.zaxxer:HikariCP:5.0.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of("17"))
}

val javaComponent: SoftwareComponent = components["java"]

tasks {
    assemble {
        dependsOn(reobfJar)
    }

    reobfJar {
        val file = File("$rootDir/jars")

        if (!file.exists()) file.mkdirs()

        outputJar.set(layout.buildDirectory.file("$file/${rootProject.name}-${rootProject.version}.jar"))
    }

    shadowJar {
        exclude("**/META-INF/**")
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

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = rootProject.group.toString()
                artifactId = "${rootProject.name.lowercase()}-paper"
                version = rootProject.version.toString()

                from(javaComponent)
            }
        }

        repositories {
            maven {
                credentials {
                    this.username = System.getenv("gradle_username")
                    this.password = System.getenv("gradle_password")
                }

                if (isSnapshot) {
                    url = uri("https://repo.crazycrew.us/snapshots/")
                    return@maven
                }

                url = uri("https://repo.crazycrew.us/releases/")
            }
        }
    }
}