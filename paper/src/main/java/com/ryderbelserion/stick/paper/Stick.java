package com.ryderbelserion.stick.paper;

import com.ryderbelserion.stick.core.storage.FileHandler;
import java.io.File;
import java.nio.file.Path;

public class Stick {

    private final FileHandler fileHandler;
    private final String projectName;
    private final Path path;

    private static String projectPrefix;

    public Stick(Path projectPath, String projectName) {
        this.projectName = projectName;

        // Create directory.
        this.path = projectPath;

        File file = this.path.toFile();
        file.mkdir();

        fileHandler = new FileHandler();
    }

    public void setPrefix(String value) {
        projectPrefix = value;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public Path getPath() {
        return this.path;
    }

    public static String getProjectPrefix() {
        return projectPrefix;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}