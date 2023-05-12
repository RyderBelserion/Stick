package com.ryderbelserion.estel.paper.storage;

import com.ryderbelserion.estel.common.storage.FileExtension;
import com.ryderbelserion.estel.common.storage.FileManager;
import com.ryderbelserion.estel.common.utils.FileUtils;
import com.ryderbelserion.estel.paper.storage.json.JsonLoader;
import java.io.File;
import java.nio.file.Path;

public class FileHandler implements FileManager {

    private JsonLoader jsonLoader;

    @Override
    public void addFile(FileExtension fileExtension) {
        switch (fileExtension.getType()) {
            case JSON -> {
                this.jsonLoader = new JsonLoader(fileExtension);
                this.jsonLoader.load();
            }

            case SQLITE -> {

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

            case SQLITE -> {

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

    private void extract(String value, Path directory) {
        File newDirectory = new File(directory + value);

        newDirectory.mkdir();

        FileUtils.extract(value, directory, false);
    }
}