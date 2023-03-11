@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("crazycore.paper-plugin")

    id("com.github.johnrengelman.shadow") version "8.1.0"
}

dependencies {
    compileOnly(libs.papermc)

    compileOnly(libs.mojang)

    api(project(":crazycore-api"))
}

tasks {
    shadowJar {
        archiveBaseName.set("${rootProject.name.lowercase()}+${projectDir.name}+${rootProject.version}.jar")
    }

    javadoc {
        title = "CrazyCore Paper - ${rootProject.version}"
        description = "The paper extension of CrazyCore"
    }
}

publishing {
    publications {
        repositories {
            maven("https://repo.crazycrew.us/libraries") {
                name = "crazycrew"
                // Used for locally publishing.
                credentials(PasswordCredentials::class)

                credentials {
                    username = System.getenv("REPOSITORY_USERNAME")
                    password = System.getenv("REPOSITORY_PASSWORD")
                }
            }
        }

        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = "${rootProject.name.lowercase()}-${projectDir.name}"
            version = rootProject.version.toString()

            from(components["java"])
        }
    }
}