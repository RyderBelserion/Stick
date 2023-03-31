plugins {
    id("crazycore.paper-plugin")

    id("xyz.jpenilla.run-paper") version "2.0.1"
}

dependencies {
    api(project(":crazycore-paper"))
}

val description = "A test plugin."
val version = "1.0.0"
val group = "us.crazycrew.example"
val name = "CrazyExample"

tasks {
    shadowJar {
        archiveFileName.set("${name}+$version.jar")
    }

    runServer {
        minecraftVersion("1.19.4")
    }

    processResources {
        filesMatching("paper-plugin.yml") {
            expand(
                "version" to version,
                "description" to description
            )
        }
    }
}