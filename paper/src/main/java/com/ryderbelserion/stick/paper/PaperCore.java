package com.ryderbelserion.stick.paper;

import com.ryderbelserion.stick.core.StickCore;
import com.ryderbelserion.stick.core.registry.senders.types.Console;
import com.ryderbelserion.stick.core.storage.FileHandler;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;

public class PaperCore implements StickCore {

    private final String projectName;
    private final Path projectPath;
    private final FileHandler fileHandler;

    private String projectPrefix;

    private PaperConsole paperConsole;

    public PaperCore(String projectName, Path projectPath) {
        try {
            Field api = StickCore.Provider.class.getDeclaredField("api");
            api.setAccessible(true);
            api.set(null, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.projectName = projectName;
        this.projectPath = projectPath;

        // Create directory.
        File file = this.projectPath.toFile();
        file.mkdir();

        this.fileHandler = new FileHandler();
    }

    @Override
    public @NotNull String getProjectName() {
        return this.projectName;
    }

    @Override
    public @NotNull String getProjectPrefix() {
        return this.projectPrefix;
    }

    public void setProjectPrefix(String value) {
        this.projectPrefix = value;
    }

    @Override
    public @NotNull Path getDirectory() {
        return this.projectPath;
    }

    @Override
    public @NotNull FileHandler getFileHandler() {
        return this.fileHandler;
    }

    public void setPaperConsole(PaperConsole paperConsole) {
        this.paperConsole = paperConsole;
    }

    @Override
    public @NotNull Console getConsole() {
        return this.paperConsole;
    }
}