package us.crazycrew.example;


import us.crazycrew.crazycore.files.FileExtension;
import us.crazycrew.crazycore.files.FileManager;
import us.crazycrew.crazycore.files.annotations.Comment;
import us.crazycrew.crazycore.files.annotations.Path;
import us.crazycrew.crazycore.files.enums.FileType;

public class ExampleConfig extends FileExtension {

    @Path("example.toggle")
    @Comment(value = "Example boolean", blockType = false)
    public static boolean EXAMPLE_BOOLEAN = false;

    public ExampleConfig() {
        super("config.yml", FileType.YAML);
    }

    public static void reload(FileManager fileManager) {
        fileManager.addFile(new ExampleConfig());
    }
}