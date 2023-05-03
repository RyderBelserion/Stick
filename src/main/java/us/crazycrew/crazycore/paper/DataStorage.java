package us.crazycrew.crazycore.paper;

import us.crazycrew.crazycore.paper.files.FileExtension;
import us.crazycrew.crazycore.paper.files.FileManager;
import us.crazycrew.crazycore.paper.files.enums.FileType;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataStorage extends FileExtension {

    public DataStorage(Path path) {
        super("users.json", path, FileType.JSON);
    }

    private final static HashMap<UUID, String> users = new HashMap<>();

    public static void addUser(UUID uuid, String value) {
        //users.computeIfAbsent(uuid, key -> value);
        if (users.containsKey(uuid)) return;

        users.put(uuid, value);
    }

    public static void removeUser(UUID uuid) {
        users.computeIfPresent(uuid, (other, value) -> users.remove(uuid));
    }

    public static Map<UUID, String> getUsers() {
        return Collections.unmodifiableMap(users);
    }

    public static void load(FileManager fileManager, Path path) {
        fileManager.addFile(new DataStorage(path).setData(true));
    }

    public static void save(FileManager fileManager, Path path) {
        fileManager.saveFile(new DataStorage(path).setData(true));
    }
}