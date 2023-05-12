package com.ryderbelserion.estel.common.storage;

import com.ryderbelserion.estel.common.storage.enums.StorageType;
import java.io.File;
import java.nio.file.Path;

public abstract class FileExtension {

    private final String name;
    private final Path path;
    private final StorageType type;

    public FileExtension(Path path, StorageType type) {
        this.path = path;

        this.name = path.toFile().getName();

        this.type = type;
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