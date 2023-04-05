package us.crazycrew.crazycore.paper.player;

import us.crazycrew.crazycore.CrazyCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.crazycrew.crazycore.registry.player.PlayerHandler;

/**
 * Description: Monitors players when they leave/join the server to update the player registry.
 */
public class PaperPlayerHandler implements PlayerHandler, Listener {

    /**
     * Runs when a player joins the server.
     *
     * @param event player join event
     */
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        PaperPlayerRegistry registry = (PaperPlayerRegistry) CrazyCore.api().getPlayerRegistry();

        onJoin(registry.register(event.getPlayer()));
    }

    /**
     * Runs when a player quits the server.
     *
     * @param event player quit event
     */
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        PaperPlayerRegistry registry = (PaperPlayerRegistry) CrazyCore.api().getPlayerRegistry();

        onQuit(registry.unregister(event.getPlayer()));
    }
}