plugins {
    id("crazycore.root-plugin")
}

dependencies {
    // JetBrains
    api(libs.jetbrains)

    // Gson.
    api(libs.gson)

    // Yaml.
    api(libs.yaml)

    // Kotlin.
    api(libs.ktor.core)
    api(libs.ktor.cio)
    api(libs.ktor.content)
    api(libs.ktor.gson)

    // Kyori.
    compileOnly(libs.kyori)
    compileOnly(libs.kyori.mm)
}

tasks {
    shadowJar {
        archiveBaseName.set("${rootProject.name}+Api+${rootProject.version}.jar")

        listOf(
            "org.jetbrains"
        ).forEach { pack ->
            relocate(pack, "${rootProject.group}.$pack")
        }
    }

    javadoc {
        title = "CrazyCore Base - ${rootProject.version}"
        description = "The base extension of CrazyCore"
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