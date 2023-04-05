package us.crazycrew.crazycore.paper;

import org.bukkit.entity.Player;
import us.crazycrew.crazycore.CrazyCore;
import us.crazycrew.crazycore.paper.player.PaperPlayerObject;
import us.crazycrew.crazycore.registry.keys.Key;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.registry.player.PlayerObject;

/**
 * Description: Creates a paper sender.
 */
public abstract class PaperSender extends us.crazycrew.crazycore.senders.Player {

    /**
     * Constructor to build the paper sender.
     *
     * @param key the unique key
     */
    public PaperSender(@NotNull Key key) {
        super(key);
    }

    /**
     * Get the sender from the registry or use console sender.
     *
     * @param sender the sender to check
     * @return the correct sender
     */
    public static us.crazycrew.crazycore.senders.Player getSender(CommandSender sender) {
        if (sender instanceof Player player) return CrazyCore.api().getPlayerRegistry().get(player.getUniqueId());

        return CrazyCore.api().getConsole();
    }

    /**
     * Get the sender from the registry or use console sender.
     *
     * @param sender the sender to check
     * @return the correct sender
     */
    public static CommandSender getSender(Player sender) {
        if (sender instanceof PlayerObject player) return ((PaperPlayerObject) player).getPlayer();

        return Bukkit.getConsoleSender();
    }
}