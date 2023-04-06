import com.lordcodes.turtle.shellRun

val beta = settings.versions.beta.get().toBoolean()

val hash = shellRun("git", listOf("rev-parse", "--short", "HEAD"))

rootProject.version = if (beta) hash else "0.0.0.2"
