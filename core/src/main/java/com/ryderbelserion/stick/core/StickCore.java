package com.ryderbelserion.stick.core;

import com.ryderbelserion.stick.core.registry.senders.types.Console;
import com.ryderbelserion.stick.core.storage.FileHandler;
import org.jetbrains.annotations.NotNull;
import java.nio.file.Path;

public interface StickCore {

    class Provider {
        static StickCore api;

        @NotNull
        public static StickCore api() {
            return Provider.api;
        }
    }

    @NotNull
    static StickCore api() {
        return Provider.api();
    }

    @NotNull String getProjectName();

    @NotNull String getProjectPrefix();

    @NotNull Path getDirectory();

    @NotNull FileHandler getFileHandler();

    @NotNull Console getConsole();
}