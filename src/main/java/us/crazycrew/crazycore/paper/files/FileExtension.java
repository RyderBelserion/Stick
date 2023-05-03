package us.crazycrew.crazycore.paper.files;

import us.crazycrew.crazycore.paper.files.enums.FileType;

import java.io.File;
import java.nio.file.Path;

/**
 * Description: An abstract class implemented which obtains required information to create files.
 */
public abstract class FileExtension {

    private final String fileName;
    private final Path filePath;
    private final FileType fileType;

    private boolean isData;

    /**
     * Constructor to build a file at a specific path.
     *
     * @param fileName the file name
     * @param filePath the file path
     */
    public FileExtension(final String fileName, final Path filePath, final FileType fileType) {
        this.fileName = fileName;

        this.filePath = filePath;

        this.fileType = fileType;
    }

    /**
     * Sets whether we use pretty printing or not.
     * Only valid for JSON File Type!
     *
     * @param value true or false
     * @return the file extension object
     */
    public FileExtension setData(boolean value) {
        this.isData = value;

        return this;
    }

    /**
     * @return true or false
     */
    public boolean isData() {
        return this.isData;
    }

    /**
     * @return the file type
     */
    public FileType getFileType() {
        return this.fileType;
    }

    /**
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
    }

    public File getFile() {
        return new File(this.filePath.toFile(), this.fileName);
    }

    /**
     * @return the file path
     */
    public Path getFilePath() {
        return this.filePath;
    }
}