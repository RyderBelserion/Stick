package us.crazycrew.crazycore.paper;

import us.crazycrew.crazycore.files.FileHandler;
import us.crazycrew.crazycore.paper.files.PaperFileManager;
import java.io.File;
import java.nio.file.Path;

/**
 * Description: A paper manager for plugins to create an instance of.
 */
public class PaperCore {

    private final PaperFileManager paperFileManager;
    private final FileHandler fileHandler;
    private final Path projectPath;

    /**
     * A constructor to pass into JavaPlugin
     *
     * @param projectPath the project path
     */
    public PaperCore(Path projectPath) {
        this.projectPath = projectPath;

        // Create directory.
        File file = this.projectPath.toFile();
        file.mkdir();

        this.fileHandler = new FileHandler();

        this.paperFileManager = new PaperFileManager();
    }

    /**
     * @return the project path
     */
    public Path getDirectory() {
        return this.projectPath;
    }

    /**
     * @return the file handler instance
     */
    public FileHandler getFileHandler() {
        return this.fileHandler;
    }

    /**
     * @return the file manager instance for paper
     */
    public PaperFileManager getPaperFileManager() {
        return this.paperFileManager;
    }
}