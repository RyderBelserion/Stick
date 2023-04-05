package us.crazycrew.crazycore.paper;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.CrazyCore;
import us.crazycrew.crazycore.files.FileHandler;
import us.crazycrew.crazycore.paper.files.PaperFileManager;
import us.crazycrew.crazycore.paper.player.PaperPlayerHandler;
import us.crazycrew.crazycore.paper.player.PaperPlayerRegistry;
import us.crazycrew.crazycore.registry.player.PlayerRegistry;
import us.crazycrew.crazycore.senders.Console;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;

/**
 * Description: A paper manager for plugins to create an instance of.
 */
public class PaperCore implements CrazyCore {

    private final PaperFileManager fileManager;
    private final FileHandler fileHandler;
    private final String projectName;
    private final Path path;

    private PaperPlayerRegistry playerRegistry;
    private PaperConsole console;
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

        this.fileManager = new PaperFileManager();
    }

    /**
     * Called in the plugin onEnable.
     * Creates the player registry and adds the listener.
     *
     * @param plugin instance of the plugin
     */
    public void createPlayerRegistry(JavaPlugin plugin) {
        if (this.playerRegistry == null) setPlayerRegistry(new PaperPlayerRegistry());

        plugin.getServer().getPluginManager().registerEvents(new PaperPlayerHandler(), plugin);
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

    /**
     * @return the file manager instance for paper
     */
    public PaperFileManager getFileManager() {
        return this.fileManager;
    }

    /**
     * Sets the paper console variable.
     *
     * @param paperConsole the paper console instance
     */
    public void setConsole(PaperConsole paperConsole) {
        this.console = paperConsole;
    }

    /**
     * @return the paper console instance
     */
    @Override
    public @NotNull Console getConsole() {
        return this.console;
    }

    /**
     * Sets the paper player registry variable.
     *
     * @param paperPlayerRegistry the paper player registry instance
     */
    public void setPlayerRegistry(PaperPlayerRegistry paperPlayerRegistry) {
        this.playerRegistry = paperPlayerRegistry;
    }

    @Override
    public @NotNull PlayerRegistry getPlayerRegistry() {
        return this.playerRegistry;
    }
}