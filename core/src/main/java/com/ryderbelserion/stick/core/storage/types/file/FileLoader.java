package com.ryderbelserion.stick.core.storage.types.file;

public interface FileLoader {

    void load();

    void save();

    String getImplName();

}