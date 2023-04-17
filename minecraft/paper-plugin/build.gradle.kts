@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("crazycore.paper-plugin")

    alias(settings.plugins.run.paper)
}

dependencies {
    api(project(":crazycore-paper"))
}

tasks {
    reobfJar {
        val file = File("$rootDir/jars")

        if (!file.exists()) file.mkdirs()

        outputJar.set(layout.buildDirectory.file("$file/${rootProject.name}-Paper-${rootProject.version}.jar"))
    }

    runServer {
        minecraftVersion("1.19.4")
    }
}