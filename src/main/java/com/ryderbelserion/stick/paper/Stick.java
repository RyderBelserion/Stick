package com.ryderbelserion.stick.paper;

import com.ryderbelserion.stick.paper.storage.FileHandler;
import java.io.File;
import java.nio.file.Path;

/**
 * Description: A paper manager for plugins to create an instance of.
 */
public class Stick {

    private final FileHandler fileHandler;
    private final String projectName;
    private final Path path;

    private static String projectPrefix;

    /**
     * A constructor to pass into JavaPlugin
     *
     * @param projectPath the project path
     */
    public Stick(Path projectPath, String projectName) {
        this.projectName = projectName;

        // Create directory.
        this.path = projectPath;

        File file = this.path.toFile();
        file.mkdir();

        fileHandler = new FileHandler();
    }

    /**
     * Sets the project prefix
     *
     * @param value the project prefix
     */
    public void setPrefix(String value) {
        projectPrefix = value;
    }

    /**
     * @return the project name
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * @return the project path.
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * @return the project prefix
     */
    public static String getProjectPrefix() {
        return projectPrefix;
    }

    /**
     * @return the file handler instance
     */
    public FileHandler getFileHandler() {
        return fileHandler;
    }
}