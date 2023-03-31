plugins {
    id("crazycore.paper-plugin")
}

dependencies {
    compileOnly(libs.mojang)

    api(project(":crazycore-api"))
}

tasks {
    reobfJar {
        val file = File("$rootDir/jars")

        if (!file.exists()) file.mkdirs()

        outputJar.set(layout.buildDirectory.file("$file/${rootProject.name}-Paper-${rootProject.version}.jar"))
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