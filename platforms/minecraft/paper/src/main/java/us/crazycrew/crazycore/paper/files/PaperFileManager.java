package us.crazycrew.crazycore.paper.files;

import us.crazycrew.crazycore.CrazyLogger;
import us.crazycrew.crazycore.files.FileExtension;
import us.crazycrew.crazycore.files.FileManager;
import us.crazycrew.crazycore.files.types.JsonManager;
import us.crazycrew.crazycore.utils.FileUtils;
import java.io.File;
import java.nio.file.Path;

/*
 * Description: A paper version of the file manager.
*/
public final class PaperFileManager extends FileManager {

    private JsonManager jsonExtension;
    // private YamlManager yamlExtension;

    /**
     * Constructor.

    public PaperFileManager() {}

    /**
     * Adds a file to the server.
     *
     * @param fileExtension the {@link FileExtension} to be added
     */
    @Override
    public void addFile(FileExtension fileExtension) {
        switch (fileExtension.getFileType()) {
            case JSON -> {
                this.jsonExtension = new JsonManager(fileExtension);

                this.jsonExtension.load();
            }

            //case YAML -> {
            //    this.yamlExtension = new YamlManager(fileExtension);

            //    this.yamlExtension.handle();
            //}
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
                this.jsonExtension = new JsonManager(fileExtension);

                this.jsonExtension.save();
            }

            //case YAML -> {
            //    this.yamlExtension = new YamlManager(fileExtension);

            //    this.yamlExtension.handle();
            //}
        }
    }

    /**
     * Removes a file from the server.
     *
     * @param fileExtension the {@link FileExtension} to remove from the server
     */
    @Override
    public void removeFile(FileExtension fileExtension) {
        File newFile = new File(fileExtension.getFilePath() + "/" + fileExtension.getFileName());

        if (newFile.exists()) newFile.delete();
    }

    @Override
    public File getFile(FileExtension fileExtension) {
        return new File(fileExtension.getFilePath() + "/" + fileExtension.getFileName());
    }

    /**
     * Extracts a set of files into the directory while creating the said directory.
     *
     * @param value the folder name
     * @param directory the output directory
     */
    public void extract(String value, Path directory) {
        File newDirectory = new File(directory + value);

        if (newDirectory.mkdirs()) CrazyLogger.debug("Created " + newDirectory.getName() + " folder.");

        FileUtils.extract(value, directory, false);
    }
}