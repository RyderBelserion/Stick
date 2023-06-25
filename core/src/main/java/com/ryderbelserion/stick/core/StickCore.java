package com.ryderbelserion.stick.core;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public abstract class StickCore {

    public static @NotNull StickCore core() {
        return Provider.core();
    }

    private final Attributes manifest;
    private String id;

    public StickCore() {
        try {
            Field core = Provider.class.getDeclaredField("core");
            core.setAccessible(true);
            core.set(null, this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Manifest manifest;
        try (JarFile file = new JarFile(getJarDirectory().toFile())) {
            JarEntry entry = file.getJarEntry("META-INF/MANIFEST.MF");

            try (InputStream in = new BufferedInputStream(file.getInputStream(entry))) {
                manifest = new Manifest(in);
            }
        } catch (IOException e) {
            manifest = new Manifest();
            e.printStackTrace();
        }

        this.manifest = manifest.getMainAttributes();
    }

    public abstract Path getDirectory();

    public abstract Path getJarDirectory();

    public abstract String getPlatform();

    public String getVersionCommit() {
        if (this.id == null) {
            this.id = this.manifest.getValue("Git-Commit");

            if (this.id == null) this.id = "unknown";
        }

        return this.id;
    }

    public abstract String getVersion();

    protected static final class Provider {
        static StickCore core;

        static @NotNull StickCore core() {
            return Provider.core;
        }
    }
}