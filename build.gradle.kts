import com.lordcodes.turtle.shellRun

rootProject.version = shellRun("git", listOf("rev-parse", "--short", "HEAD"))