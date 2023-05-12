package us.crazycrew.crazycore.paper.files.types;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Location;
import us.crazycrew.crazycore.paper.files.FileExtension;
import us.crazycrew.crazycore.paper.files.types.adapters.LocationTypeAdapter;
import java.io.*;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

/**
 * Description: Creates .json files on its own thread.
 */
public class JsonManager {

    private final FileExtension fileExtension;

    private final File file;

    private final Gson gson;

    /**
     * The constructor to build information we need for json files.
     *
     * @param fileExtension the class that represents the values we need
     */
    public JsonManager(FileExtension fileExtension) {
        this.fileExtension = fileExtension;

        this.file = this.fileExtension.getFile();

        GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Location.class, new LocationTypeAdapter());

        this.gson = this.fileExtension.isData() ? gsonBuilder.create() : gsonBuilder.setPrettyPrinting().create();
    }

    /**
     * Load the json file or save it if just created.
     */
    public void load() {
        try {
            if (this.file.createNewFile()) {
                save();

                return;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(this.file), StandardCharsets.UTF_8)) {
            this.gson.fromJson(reader, this.fileExtension.getClass());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Save the json file.
     */
    public void save() {
        try {
            if (!this.file.exists()) this.file.createNewFile();

            write();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Writes to the json file.
     *
     * @throws IOException if write fails
     */
    public void write() throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(this.file), StandardCharsets.UTF_8)) {
            String values = this.gson.toJson(this.fileExtension, this.fileExtension.getClass());

            writer.write(values);
        }
    }
}