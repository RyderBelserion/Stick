package com.ryderbelserion.stick.paper.storage.types.file.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.types.file.FileLoader;
import com.ryderbelserion.stick.paper.storage.types.file.json.adapters.LocationTypeAdapter;
import org.bukkit.Location;
import java.io.*;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

public class JsonLoader implements FileLoader {

    private final FileExtension fileExtension;

    private final File file;

    private final Gson gson;

    public JsonLoader(FileExtension fileExtension) {
        this.fileExtension = fileExtension;

        this.file = this.fileExtension.getFile();

        GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Location.class, new LocationTypeAdapter());

        this.gson = gsonBuilder.create();
    }

    @Override
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

    @Override
    public void save() {
        try {
            if (!this.file.exists()) this.file.createNewFile();

            write();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void write() throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(this.file), StandardCharsets.UTF_8)) {
            String values = this.gson.toJson(this.fileExtension, this.fileExtension.getClass());

            writer.write(values);
        }
    }
}