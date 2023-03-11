package us.crazycrew.crazycore.paper.player;

import org.bukkit.entity.Player;
import us.crazycrew.crazycore.CrazyCore;
import us.crazycrew.crazycore.player.CrazyPlayer;
import us.crazycrew.crazycore.registry.keys.Key;
import us.crazycrew.crazycore.registry.senders.types.Sender;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Description: Creates a paper sender.
 */
public abstract class PaperSender extends Sender {

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
    public static Sender getSender(CommandSender sender) {
        if (sender instanceof Player player) return CrazyCore.api().getPlayerRegistry().get(player.getUniqueId());

        return CrazyCore.api().getConsole();
    }

    /**
     * Get the sender from the registry or use console sender.
     *
     * @param sender the sender to check
     * @return the correct sender
     */
    public static CommandSender getSender(Sender sender) {
        if (sender instanceof CrazyPlayer player) return ((PaperPlayer) player).getPlayer();

        return Bukkit.getConsoleSender();
    }
}