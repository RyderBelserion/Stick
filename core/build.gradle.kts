plugins {
    id("root-plugin")
}

dependencies {
    implementation("me.carleslc.Simple-YAML", "Simple-Yaml", "1.8.3")

    implementation("com.google.code.gson", "gson", "2.10.1")
    implementation("org.jetbrains", "annotations", "24.0.0")
}

tasks {
    shadowJar {
        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.deps.$pkg")

        reloc("me.carleslc.Simple-YAML")
        reloc("com.google.code.gson")
        reloc("org.jetbrains")

        exclude("**/META-INF/**")
    }
}