package us.crazycrew.crazycore.paper;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.UUID;

public class CrazyTest extends JavaPlugin implements Listener {

    private CrazyCore crazyCore;

    @Override
    public void onEnable() {
        this.crazyCore = new CrazyCore(getDataFolder().toPath(), "CrazyCrates");

        getServer().getPluginManager().registerEvents(this, this);

        DataStorage.load(this.crazyCore.getFileHandler(), getDataFolder().toPath());
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        DataStorage.addUser(event.getPlayer().getUniqueId(), UUID.randomUUID().toString());

        getLogger().warning("Current Size: " + DataStorage.getUsers().size());
    }

    public CrazyCore getCrazyCore() {
        return this.crazyCore;
    }
}