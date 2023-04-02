package us.crazycrew.crazycore.paper;

import org.bukkit.plugin.java.JavaPlugin;

public class CrazyExample extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().warning("Guten Tag!");
    }

    @Override
    public void onDisable() {
        getLogger().warning("Good Bye!");
    }
}