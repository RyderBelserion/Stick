package us.crazycrew.crazycore.paper;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.CrazyCore;
import us.crazycrew.crazycore.files.FileHandler;
import us.crazycrew.crazycore.paper.files.PaperFileManager;
import us.crazycrew.crazycore.paper.player.PaperPlayerListener;
import us.crazycrew.crazycore.paper.player.PaperPlayerRegistry;
import us.crazycrew.crazycore.player.CrazyPlayerRegistry;
import us.crazycrew.crazycore.registry.senders.types.Console;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;

/**
 * Description: A paper manager for plugins to create an instance of.
 */
public class PaperCore implements CrazyCore {

    private final PaperFileManager paperFileManager;
    private final FileHandler fileHandler;
    private final String projectName;
    private final Path projectPath;

    private PaperPlayerRegistry paperPlayerRegistry;
    private PaperConsole paperConsole;
    private String projectPrefix;

    /**
     * A constructor to pass into JavaPlugin
     *
     * @param projectName the project name
     * @param projectPath the project path
     */
    public PaperCore(String projectName, Path projectPath) {
        try {
            Field api = CrazyCore.CrazyProvider.class.getDeclaredField("api");
            api.setAccessible(true);
            api.set(null, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.projectName = projectName;

        this.projectPath = projectPath;

        // Create directory.
        File file = this.projectPath.toFile();
        //noinspection ResultOfMethodCallIgnored
        file.mkdir();

        this.fileHandler = new FileHandler();

        this.paperFileManager = new PaperFileManager();
    }

    /**
     * Called in the plugin onEnable.
     * Creates the player registry and adds the listener.
     *
     * @param plugin instance of the plugin
     */
    public void createPlayerRegistry(JavaPlugin plugin) {
        if (this.paperPlayerRegistry == null) setPaperPlayerRegistry(new PaperPlayerRegistry());

        plugin.getServer().getPluginManager().registerEvents(new PaperPlayerListener(), plugin);
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

    /**
     * Sets the project prefix
     *
     * @param value the project prefix
     */
    public void setProjectPrefix(String value) {
        this.projectPrefix = value;
    }

    /**
     * @return the project path
     */
    @Override
    public @NotNull Path getDirectory() {
        return this.projectPath;
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
    public PaperFileManager getPaperFileManager() {
        return this.paperFileManager;
    }

    /**
     * Sets the paper console variable.
     *
     * @param paperConsole the paper console instance
     */
    public void setPaperConsole(PaperConsole paperConsole) {
        this.paperConsole = paperConsole;
    }

    /**
     * @return the paper console instance
     */
    @Override
    public @NotNull Console getConsole() {
        return this.paperConsole;
    }

    /**
     * Sets the paper player registry variable.
     *
     * @param paperPlayerRegistry the paper player registry instance
     */
    public void setPaperPlayerRegistry(PaperPlayerRegistry paperPlayerRegistry) {
        this.paperPlayerRegistry = paperPlayerRegistry;
    }

    @Override
    public @NotNull CrazyPlayerRegistry getPlayerRegistry() {
        return this.paperPlayerRegistry;
    }
}