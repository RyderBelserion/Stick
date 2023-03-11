package us.crazycrew.crazycore.utils;

import us.crazycrew.crazycore.CrazyLogger;
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

    /**
     * Extracts files from inside the .jar into an output
     *
     * @param input the directory in the .jar
     * @param output the output wherever you use this.
     * @param replace if we should replace or not.
     */
    public static void extract(String input, Path output, boolean replace) {
        URL directory = FileUtils.class.getResource(input);

        if (directory == null) CrazyLogger.info("Could not find the directory " + input);

        assert directory != null;
        if (!directory.getProtocol().equals("jar")) CrazyLogger.info("Failed extracting files because the directory protocol is not from a .jar file.");

        ZipFile jar;
        try {
            CrazyLogger.info("Starting to extract files from " + input + " directory in the jar file.");

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
                if (exists) {
                    CrazyLogger.info("The file " + outFile.toFile().getName() + " already exists.");

                    return;
                }

                try {
                    Files.createDirectories(outFile);

                    CrazyLogger.info("The directory " + input + " has been created.");
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
                String message = """
                        An error has occurred while copying files out of a jar.
                        
                        Cause: %cause%
                        """;

                CrazyLogger.debug(message.replaceAll("%cause%", exception.getMessage()));
            }
        }
    }
}