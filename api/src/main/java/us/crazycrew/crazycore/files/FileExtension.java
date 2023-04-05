package us.crazycrew.crazycore.files;

import us.crazycrew.crazycore.CrazyCore;
import us.crazycrew.crazycore.files.enums.FileType;
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
     * Constructor to build a file.
     *
     * @param fileName the name of the file
     */
    public FileExtension(final String fileName, final FileType fileType) {
        this.fileName = fileName;

        this.filePath = CrazyCore.api().getDirectory();

        this.fileType = fileType;
    }

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

    /**
     * @return the file path
     */
    public Path getFilePath() {
        return this.filePath;
    }
}