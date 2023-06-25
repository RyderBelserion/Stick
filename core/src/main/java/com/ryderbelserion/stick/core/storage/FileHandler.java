package com.ryderbelserion.stick.core.storage;

import com.ryderbelserion.stick.core.storage.types.file.json.JsonLoader;
import com.ryderbelserion.stick.core.storage.types.file.yaml.YamlLoader;
import com.ryderbelserion.stick.core.storage.types.sql.file.SqliteLoader;
import com.ryderbelserion.stick.core.utils.FileUtils;
import java.io.File;
import java.nio.file.Path;

public class FileHandler implements FileManager {

    private JsonLoader jsonLoader;
    private YamlLoader yamlLoader;
    private SqliteLoader sqliteLoader;

    @Override
    public void addFile(FileExtension fileExtension) {
        switch (fileExtension.getType()) {

            case JSON -> {
                this.jsonLoader = new JsonLoader(fileExtension);
                this.jsonLoader.load();
            }

            case YAML -> {
                this.yamlLoader = new YamlLoader(fileExtension);
                this.yamlLoader.load();
            }

            case SQLITE -> {
                /*this.sqliteLoader = new SqliteLoader(fileExtension.getName(), fileExtension.getPath());

                if (this.sqliteLoader.getConnection() != null) {
                    try {
                        Bukkit.getLogger().warning(String.valueOf(this.sqliteLoader.getConnection().isClosed()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }*/
            }

            default -> throw new IllegalStateException("Unexpected value: " + fileExtension.getType());
        }
    }

    @Override
    public void saveFile(FileExtension fileExtension) {
        switch (fileExtension.getType()) {
            case JSON -> {
                this.jsonLoader = new JsonLoader(fileExtension);
                this.jsonLoader.save();
            }

            case YAML -> {
                this.yamlLoader = new YamlLoader(fileExtension);
                this.yamlLoader.save();
            }

            case SQLITE -> {
                //this.sqliteLoader.shutdown();
            }

            default -> throw new IllegalStateException("Unexpected value: " + fileExtension.getType());
        }
    }

    @Override
    public void removeFile(FileExtension fileExtension) {
        File file = fileExtension.getPath().toFile();

        if (file.exists()) file.delete();
    }

    @Override
    public File getFile(FileExtension fileExtension) {
        return fileExtension.getFile();
    }

    public void extract(String value, Path directory) {
        File newDirectory = new File(directory + value);

        newDirectory.mkdir();

        FileUtils.extract(value, directory, false);
    }
}