package us.crazycrew.crazycore.paper;

import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.CrazyCore;
import us.crazycrew.crazycore.files.FileHandler;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;

/**
 * Description: A paper manager for plugins to create an instance of.
 */
public class PaperCore implements CrazyCore {

    private final FileHandler fileHandler;
    private final String projectName;
    private final Path path;

    private Audience audience;
    private String projectPrefix;

    /**
     * A constructor to pass into JavaPlugin
     *
     * @param projectName the project name
     * @param projectPath the project path
     */
    public PaperCore(String projectName, Path projectPath) {
        try {
            Field api = CrazyCore.Provider.class.getDeclaredField("api");
            api.setAccessible(true);
            api.set(null, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.projectName = projectName;

        this.path = projectPath;

        // Create directory.
        File file = this.path.toFile();
        //noinspection ResultOfMethodCallIgnored
        file.mkdir();

        this.fileHandler = new FileHandler();
    }

    /**
     * @return the project name
     */
    @Override
    public @NotNull String getProjectName() {
        return this.projectName;
    }

    /**
     * @return the project prefix
     */
    @Override
    public @NotNull String getProjectPrefix() {
        return this.projectPrefix;
    }

    @Override
    public @NotNull Platforms getPlatform() {
        return Platforms.PAPER;
    }

    /**
     * Sets the project prefix
     *
     * @param value the project prefix
     */
    public void setPrefix(String value) {
        this.projectPrefix = value;
    }

    /**
     * @return the project path
     */
    @Override
    public @NotNull Path getDirectory() {
        return this.path;
    }

    /**
     * @return the file handler instance
     */
    @Override
    public @NotNull FileHandler getFileHandler() {
        return this.fileHandler;
    }

    public void setConsole(Audience audience) {
        this.audience = audience;
    }

    public @NotNull Audience getAudience() {
        return this.audience;
    }
}