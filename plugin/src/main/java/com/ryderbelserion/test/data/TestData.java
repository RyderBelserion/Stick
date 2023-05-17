package com.ryderbelserion.test.data;

import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import com.ryderbelserion.test.TestPlugin;
import java.nio.file.Path;

public class TestData extends FileExtension {

    public TestData(Path path) {
        super("test-data.db", path, StorageType.SQLITE);
    }

    public static void load(Path path) {
        TestPlugin.getPlugin().getStick().getFileHandler().addFile(new TestData(path));
    }
}