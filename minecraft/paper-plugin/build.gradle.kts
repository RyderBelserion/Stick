@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("crazycore.paper-plugin")

    alias(settings.plugins.run.paper)
}

dependencies {
    api(project(":crazycore-paper"))
}

tasks {
    runServer {
        minecraftVersion("1.19.4")
    }
}