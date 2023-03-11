package us.crazycrew.example;

import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.paper.PaperCore;
import us.crazycrew.crazycore.paper.PaperConsole;
import us.crazycrew.crazycore.CrazyLogger;
import us.crazycrew.crazycore.paper.player.PaperPlayerRegistry;
import java.util.logging.LogManager;

@SuppressWarnings("UnstableApiUsage")
public class CrazyLoader implements PluginBootstrap {

    private PaperCore paperCore;

    @Override
    public void bootstrap(@NotNull PluginProviderContext context) {
        this.paperCore = new PaperCore(context.getConfiguration().getName(), context.getDataDirectory());
    }

    @Override
    public @NotNull JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
        // Create the player registry.
        this.paperCore.setPaperPlayerRegistry(new PaperPlayerRegistry());

        // Create the console instance.
        this.paperCore.setPaperConsole(new PaperConsole());

        // Set the project prefix.
        this.paperCore.setProjectPrefix("<gradient:#fe5f55:#6b55b5>[CrazyExample]</gradient> ");

        // Set the logger name and create it.
        CrazyLogger.setName(this.paperCore.getProjectName());

        // Add the logger manager.
        LogManager.getLogManager().addLogger(CrazyLogger.getLogger());

        // Create the plugin instance.
        return new CrazyExample(this.paperCore);
    }
}