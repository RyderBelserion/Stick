package com.ryderbelserion.stick.paper.storage;

import java.io.File;

public interface FileManager {

    void addFile(FileExtension fileExtension);

    void saveFile(FileExtension fileExtension);

    void removeFile(FileExtension fileExtension);

    File getFile(FileExtension fileExtension);

}