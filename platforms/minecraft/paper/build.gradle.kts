@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("crazycore.paper-plugin")
}

dependencies {
    compileOnly(libs.papermc)

    compileOnly(libs.mojang)

    api(project(":crazycore-api"))
}

tasks {
    shadowJar {
        archiveBaseName.set("${rootProject.name}-Paper")

        archiveClassifier.set("")
    }

    javadoc {
        title = "CrazyCore Paper - ${rootProject.version}"
        description = "The paper extension of CrazyCore"

        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
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
            artifact(tasks["shadowJar"])
        }
    }
}