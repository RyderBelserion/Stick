package us.crazycrew.crazycore.files;

import us.crazycrew.crazycore.files.types.YamlManager;
import us.crazycrew.crazycore.utils.FileUtils;
import us.crazycrew.crazycore.files.types.JsonManager;
import java.io.File;
import java.nio.file.Path;

/**
 * Description: A file handler allowing to create/handle files with ease.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class FileHandler extends FileManager {

    private JsonManager jsonManager;
    private YamlManager yamlManager;

    /**
     * Adds a file to the server.
     *
     * @param fileExtension the {@link FileExtension} to be added
     */
    @Override
    public void addFile(FileExtension fileExtension) {
        switch (fileExtension.getFileType()) {
            case JSON -> {
                this.jsonManager = new JsonManager(fileExtension);
                this.jsonManager.load();
            }

            case YAML -> {
                this.yamlManager = new YamlManager(fileExtension);
                this.yamlManager.load();
            }

            case SQLITE -> {

            }

            default -> throw new IllegalStateException("Unexpected value: " + fileExtension.getFileType());
        }
    }

    /**
     * Saves a file to the server.
     *
     * @param fileExtension the {@link FileExtension} to be saved
     */
    @Override
    public void saveFile(FileExtension fileExtension) {
        switch (fileExtension.getFileType()) {
            case JSON -> {
                this.jsonManager = new JsonManager(fileExtension);
                this.jsonManager.save();
            }

            case YAML -> {
                this.yamlManager = new YamlManager(fileExtension);
                this.yamlManager.load();
            }

            case SQLITE -> {

            }

            default -> throw new IllegalStateException("Unexpected value: " + fileExtension.getFileType());
        }
    }

    /**
     * Removes a file from the server.
     *
     * @param fileExtension the {@link FileExtension} to remove from the server
     */
    @Override
    public void removeFile(FileExtension fileExtension) {
        File file = fileExtension.getFilePath().toFile();

        if (file.exists()) file.delete();
    }

    @Override
    public File getFile(FileExtension fileExtension) {
        return fileExtension.getFilePath().toFile();
    }

    /**
     * Extracts a set of files into the directory while creating the said directory.
     *
     * @param value the folder name
     * @param directory the output directory
     */
    public void extract(String value, Path directory) {
        File newDirectory = new File(directory + value);

        newDirectory.mkdir();

        FileUtils.extract(value, directory, false);
    }
}