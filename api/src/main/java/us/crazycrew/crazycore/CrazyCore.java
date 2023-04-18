package us.crazycrew.crazycore;

import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.files.FileHandler;

import java.nio.file.Path;

public interface CrazyCore {

    final class Provider {

        static CrazyCore api;

        @NotNull
        public static CrazyCore api() {
            return Provider.api;
        }
    }

    @NotNull
    static CrazyCore api() {
        return Provider.api();
    }

    /**
     * @return the project name
     */
    @NotNull String getProjectName();

    /**
     * @return the project prefix
     */
    @NotNull String getProjectPrefix();

    /**
     * @return the platform type
     */
    @NotNull Platforms getPlatform();

    /**
     * @return the root directory
     */
    @NotNull Path getDirectory();

    /**
     * @return the file handler
     */
    @NotNull FileHandler getFileHandler();

    /**
     * @return the console sender
     */
    @NotNull Audience getAudience();

    enum Platforms {
        PAPER
    }
}