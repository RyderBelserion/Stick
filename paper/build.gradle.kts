plugins {
    id("paper-plugin")

    id("publish-task")
}

dependencies {
    api("com.ryderbelserion.stick", "stick-core", "2.2.1-snapshot")
}

tasks {
    shadowJar {
        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.deps.$pkg")

        archiveBaseName.set("${rootProject.name.lowercase()}-${project.name}")

        exclude("**/META-INF/**")
    }
}