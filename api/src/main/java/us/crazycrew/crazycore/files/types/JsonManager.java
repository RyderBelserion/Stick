package us.crazycrew.crazycore.files.types;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import us.crazycrew.crazycore.files.FileExtension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

        this.file = this.fileExtension.getFilePath().toFile();

        GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithModifiers(Modifier.TRANSIENT).excludeFieldsWithoutExposeAnnotation();

        this.gson = this.fileExtension.isData() ? gsonBuilder.create() : gsonBuilder.setPrettyPrinting().create();
    }

    /**
     * Load the json file or save it if just created.
     */
    public void load() {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(this.file), StandardCharsets.UTF_8)) {
            if (this.file.createNewFile()) {
                save();

                return;
            }

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