package com.ryderbelserion.stick.paper.plugin;

import com.ryderbelserion.stick.paper.Stick;
import com.ryderbelserion.stick.paper.StickLogger;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.logging.LogManager;

public class CrazyStarter implements PluginBootstrap {

    private Stick stick;

    @Override
    public void bootstrap(@NotNull PluginProviderContext context) {
        this.stick = new Stick(context.getDataDirectory(), context.getConfiguration().getName());
    }

    @Override
    public @NotNull JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
        this.stick.setPrefix("<red>[TestPlugin]</red> <reset>");

        StickLogger.setName(Stick.getProjectPrefix());

        LogManager.getLogManager().addLogger(StickLogger.getLogger());

        return new TestPlugin(this.stick);
    }
}