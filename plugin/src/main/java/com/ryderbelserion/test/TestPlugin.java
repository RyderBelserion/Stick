package com.ryderbelserion.test;

import com.ryderbelserion.stick.paper.Stick;
import com.ryderbelserion.stick.paper.StickLogger;
import com.ryderbelserion.test.data.TestData;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.logging.Logger;

public class TestPlugin extends JavaPlugin {

    private static TestPlugin plugin;

    private final Stick stick;

    public TestPlugin(Stick stick) {
        this.stick = stick;
    }

    @Override
    public @NotNull Logger getLogger() {
        return StickLogger.getLogger();
    }

    @Override
    public void onEnable() {
        plugin = this;

        StickLogger.warn("<red>Guten Tag!</red>");

        TestData.load(getDataFolder().toPath());
    }

    @Override
    public void onDisable() {
        StickLogger.warn("<green>Good Bye</green>");
    }

    public static TestPlugin getPlugin() {
        return plugin;
    }

    public Stick getStick() {
        return this.stick;
    }
}