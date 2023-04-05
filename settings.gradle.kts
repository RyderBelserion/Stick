dependencyResolutionManagement {
    versionCatalogs {
        create("settings") {
            from(files("gradle/settings.versions.toml"))
        }
    }

    repositories.gradlePluginPortal()
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "CrazyCore"

val lowerCase = rootProject.name.lowercase()

listOf("api").forEach(::includeProject)

listOf("paper", "paper-plugin").forEach(::includeType)

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

fun includeType(name: String) {
    include(name) {
        this.name = "$lowerCase-$name"
        this.projectDir = file("minecraft/$name")
    }
}

fun include(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}