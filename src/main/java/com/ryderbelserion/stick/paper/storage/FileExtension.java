package com.ryderbelserion.stick.paper.storage;

import com.ryderbelserion.stick.paper.storage.enums.StorageType;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class FileExtension <Types> {

    private final String name;
    private final Path path;
    private final StorageType type;

    private final WeakHashMap<Class<?>, Types> typeAdapters = new WeakHashMap<>();

    public FileExtension(String name, Path path, StorageType type) {
        this.path = path;

        this.name = name;

        this.type = type;
    }

    public void setAdapters(Class<?> classObject, Types adapters) {
        if (this.type != StorageType.JSON) return;

        if (!typeAdapters.containsKey(classObject)) this.typeAdapters.put(classObject, adapters);
    }

    public Map<Class<?>, Types> getAdapters() {
        return Collections.unmodifiableMap(this.typeAdapters);
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