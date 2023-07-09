plugins {
    id("paper-plugin")

    id("publish-task")
}

dependencies {
    api(project(":core")) {
        exclude("com.google.code.gson", "gson")
        exclude("me.carleslc.Simple-YAML", "Simple-Yaml")
        exclude("org.jetbrains", "annotations")
    }
}

tasks {
    shadowJar {
        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.deps.$pkg")

        archiveBaseName.set("${rootProject.name.lowercase()}-${project.name}")

        exclude("**/META-INF/**")
    }
}