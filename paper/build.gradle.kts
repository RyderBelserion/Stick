plugins {
    id("paper-plugin")
}

dependencies {
    api(project(":core"))
}

tasks {
    reobfJar {
        val file = File("$rootDir/jars")

        if (!file.exists()) file.mkdirs()

        outputJar.set(layout.buildDirectory.file("$file/${rootProject.name}-${rootProject.version}.jar"))
    }

    shadowJar {
        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.deps.$pkg")

        exclude("**/META-INF/**")
    }
}