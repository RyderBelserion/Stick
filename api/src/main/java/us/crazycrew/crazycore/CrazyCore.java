package us.crazycrew.crazycore;

import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.files.FileHandler;
import us.crazycrew.crazycore.player.CrazyPlayerRegistry;
import us.crazycrew.crazycore.registry.senders.types.Console;
import java.nio.file.Path;

/**
 * Description: An interface to extend/implement that contains important methods.
 */
public interface CrazyCore {

    /**
     * A class that contains static instances of CrazyCore
     */
    class CrazyProvider {
        static CrazyCore api;

        /**
         * Gets the static variable inside CrazyProvider.
         *
         * @return the static variable of crazycore
         */
        @NotNull
        public static CrazyCore api() {
            return CrazyProvider.api;
        }
    }

    /**
     * @return the static variable of crazycore
     */
    @NotNull
    static CrazyCore api() {
        return CrazyProvider.api();
    }

    /**
     * Gets the project name.
     *
     * @return the project name
     */
    @NotNull String getProjectName();

    /**
     * Gets the project prefix.
     *
     * @return the project prefix
     */
    @NotNull String getProjectPrefix();

    /**
     * Gets the main directory of the project.
     *
     * @return the main directory
     */
    @NotNull Path getDirectory();

    /**
     * Gets the file handler of the project.
     *
     * @return the file handler
     */
    @NotNull FileHandler getFileHandler();

    /**
     * @return the console sender
     */
    @NotNull Console getConsole();

    /**
     * Used to handle uuids and other data for multi-platform support.
     *
     * @return the player registry
     */
    @NotNull CrazyPlayerRegistry getPlayerRegistry();
}