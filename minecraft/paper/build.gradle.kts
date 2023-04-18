plugins {
    id("crazycore.paper-plugin")
}

dependencies {
    compileOnly(libs.mojang)

    api(project(":crazycore-api"))
}

tasks {
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

publishing {
    publications {
        repositories {
            maven("https://repo.crazycrew.us/api") {
                name = "crazycrew"
                credentials(PasswordCredentials::class)
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