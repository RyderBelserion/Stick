package com.ryderbelserion.stick.common.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Description: Extracts files from inside the .jar file.
 */
public class FileUtils {

    private FileUtils() {}

    /**
     * Extracts files from inside the .jar into an output
     *
     * @param input the directory in the .jar
     * @param output the output wherever you use this.
     * @param replace if we should replace or not.
     */
    public static void extract(String input, Path output, boolean replace) {
        URL directory = FileUtils.class.getResource(input);

        if (directory == null) return;

        if (!directory.getProtocol().equals("jar")) return;

        ZipFile jar;

        try {
            jar = ((JarURLConnection) directory.openConnection()).getJarFile();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        String filePath = input.substring(1);
        Enumeration<? extends ZipEntry> fileEntries = jar.entries();

        while (fileEntries.hasMoreElements()) {
            ZipEntry entry = fileEntries.nextElement();
            String entryName = entry.getName();

            if (!entryName.startsWith(filePath)) continue;

            Path outFile = output.resolve(entryName);
            boolean exists = Files.exists(outFile);

            if (!replace && exists) continue;

            if (entry.isDirectory()) {
                if (exists) return;

                try {
                    Files.createDirectories(outFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                continue;
            }

            try (InputStream inputStream = jar.getInputStream(entry); OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outFile.toFile()))) {
                byte[] buffer = new byte[4096];

                int readCount;

                while ((readCount = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, readCount);
                }

                outputStream.flush();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}