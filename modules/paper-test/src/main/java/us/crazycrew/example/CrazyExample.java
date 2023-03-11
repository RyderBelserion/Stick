package us.crazycrew.example;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.CrazyLogger;
import us.crazycrew.crazycore.paper.PaperCore;
import java.util.logging.Logger;

public class CrazyExample extends JavaPlugin {

    private static CrazyExample plugin;

    private final PaperCore paperCore;

    public CrazyExample(PaperCore paperCore) {
        this.paperCore = paperCore;

        plugin = this;
    }

    @Override
    public @NotNull Logger getLogger() {
        return CrazyLogger.getLogger();
    }

    @Override
    public void onEnable() {
        ExampleConfig.reload(this.paperCore.getPaperFileManager());
    }

    @Override
    public void onDisable() {

    }

    public PaperCore getCrazyCore() {
        return this.paperCore;
    }

    public static CrazyExample getPlugin() {
        return plugin;
    }
}