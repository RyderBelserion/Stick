package com.ryderbelserion.stick.paper.storage.types.file.yaml;

import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import com.ryderbelserion.stick.paper.storage.types.file.FileLoader;
import com.ryderbelserion.stick.paper.storage.types.file.yaml.keys.Comment;
import com.ryderbelserion.stick.paper.storage.types.file.yaml.keys.Path;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class YamlLoader implements FileLoader {

    private final FileExtension fileExtension;

    private final File file;

    private YamlConfiguration config;

    public YamlLoader(FileExtension fileExtension) {
        this.fileExtension = fileExtension;

        this.file = this.fileExtension.getFile();
    }

    protected void setComments(String path, @NotNull String[] comments) {
        if (comments != null) this.config.setComments(path, List.of(comments));
    }

    protected Object getValue(String path, Object def) {
        if (this.config.get(path) == null) this.config.set(path, def);

        return this.config.get(path);
    }

    @Override
    public void load() {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);

        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            Path path = field.getDeclaredAnnotation(Path.class);
            Comment comment = field.getDeclaredAnnotation(Comment.class);

            if (path == null) return;

            Object pathValue = getValue(path.value(), comment.value());

            try {
                field.set(this.fileExtension, pathValue instanceof String stringValue ? stringValue.translateEscapes() : pathValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            setComments(path.value(), comment.value());
        }

        save();
    }

    @Override
    public void save() {
        try {
            this.config.save(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getImplName() {
        return StorageType.YAML.getName();
    }
}