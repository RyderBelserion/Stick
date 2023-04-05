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

    // Adventure.
    compileOnly(libs.kyori)
    compileOnly(libs.kyori.mm)
}

tasks {
    shadowJar {
        archiveBaseName.set("${rootProject.name}-API-${rootProject.version}")

        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.dependency.$pkg")

        reloc("org.jetbrains")
    }

    javadoc {
        title = "CrazyCore Base - ${rootProject.version}"
        description = "The base extension of CrazyCore"
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

        create<MavenPublication>("api") {
            groupId = rootProject.group.toString()
            artifactId = "${rootProject.name.lowercase()}-${projectDir.name}"
            version = rootProject.version.toString()

            from(components["java"])
        }
    }
}