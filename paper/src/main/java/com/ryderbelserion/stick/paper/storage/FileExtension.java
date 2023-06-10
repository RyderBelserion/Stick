package com.ryderbelserion.stick.paper.storage;

import com.google.gson.GsonBuilder;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import java.io.File;
import java.nio.file.Path;

public abstract class FileExtension {

    private final String name;
    private final Path path;
    private final StorageType type;

    private GsonBuilder gsonBuilder;

    public FileExtension(String name, Path path, StorageType type) {
        this.path = path;

        this.name = name;

        this.type = type;
    }

    public void setGsonBuilder(GsonBuilder gsonBuilder) {
        if (type != StorageType.JSON) return;

        this.gsonBuilder = gsonBuilder;
    }

    public GsonBuilder getGsonBuilder() {
        return this.gsonBuilder;
    }

    public StorageType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public File getFile() {
        return new File(this.path.toFile(), this.name);
    }

    public Path getPath() {
        return this.path;
    }
}