package us.crazycrew.crazycore.paper.files;

import java.io.File;

/**
 * Description: The abstract file manager class to enforce strict methods.
 */
public abstract class FileManager {

    /**
     * Registers a {@link FileExtension} into the manager.
     *
     * @param fileExtension the {@link FileExtension} to be added
     */
    public abstract void addFile(FileExtension fileExtension);

    /**
     * Re-registers a {@link FileExtension} into the manager.
     *
     * @param fileExtension the {@link FileExtension} to be saved
     */
    public abstract void saveFile(FileExtension fileExtension);

    /**
     * Removes a {@link FileExtension} from the server.
     *
     * @param fileExtension the {@link FileExtension} to remove from the server
     */
    public abstract void removeFile(FileExtension fileExtension);

    /**
     * Get a {@link FileExtension} from the server.
     *
     * @param fileExtension the {@link FileExtension} to be fetched
     *
     * @return the config file
     */
    public abstract File getFile(FileExtension fileExtension);

}