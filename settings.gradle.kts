dependencyResolutionManagement {
    repositories.gradlePluginPortal()
}

pluginManagement {
    repositories {
        gradlePluginPortal()

        mavenCentral()
    }
}

val lowerCase = rootProject.name.lowercase()

listOf("api").forEach(::includeProject)

listOf("paper").forEach(::includeMinecraftType)

listOf("paper-test").forEach(::includeModule)

fun includeProject(name: String) {
    include(name) {
        this.name = "$lowerCase-$name"
    }
}

fun includeModule(name: String) {
    include(name) {
        this.name = "$lowerCase-module-$name"
        this.projectDir = file("modules/$name")
    }
}

fun includeMinecraftType(name: String) {
    include(name) {
        this.name = "$lowerCase-$name"
        this.projectDir = file("platforms/minecraft/$name")
    }
}

fun includeDiscordType(name: String) {
    include(name) {
        this.name = "$lowerCase-$name"
        this.projectDir = file("platforms/discord/$name")
    }
}

fun include(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}