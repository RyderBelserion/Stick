package us.crazycrew.crazycore.paper.player;

import us.crazycrew.crazycore.CrazyCore;
import us.crazycrew.crazycore.player.CrazyPlayerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Description: Monitors players when they leave/join the server to update the player registry.
 */
public class PaperPlayerListener implements CrazyPlayerListener, Listener {

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