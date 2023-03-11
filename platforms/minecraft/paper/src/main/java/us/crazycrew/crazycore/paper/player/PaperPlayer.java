package us.crazycrew.crazycore.paper.player;

import us.crazycrew.crazycore.player.CrazyPlayer;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Objects;
import java.util.UUID;

/**
 * Description: Allows to obtain uuids, names or send messages.
 */
public class PaperPlayer extends CrazyPlayer {

    private final Player player;

    /**
     * Builds a new player.
     *
     * @param player the player
     */
    public PaperPlayer(Player player) {
        super(createKey(player.getUniqueId()));

        this.player = player;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return the player name
     */
    @Override
    public @NotNull String getName() {
        return this.player.getName();
    }

    /**
     * @return the player uuid
     */
    @Override
    public @NotNull UUID getUUID() {
        return this.player.getUniqueId();
    }

    /**
     * Sends a message to a player.
     *
     * @param source the identity of the source of the message
     * @param message a message
     * @param type the type
     */
    @Override
    public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        this.player.sendMessage(source, message, type);
    }

    /**
     * @param instance the object to check
     * @return true or false
     */
    @Override
    public boolean equals(@Nullable Object instance) {
        if (this == instance) return true;
        if (instance == null) return false;
        if (this.getClass() != instance.getClass()) return false;
        PaperPlayer other = (PaperPlayer) instance;
        return getKey() == other.getKey() && this.player == other.player;
    }

    /**
     * @return the hash of the key / player
     */
    @Override
    public int hashCode() {
        return Objects.hash(getKey(), this.player);
    }

    /**
     * @return json string of player
     */
    @Override
    public String toString() {
        return "PaperPlayer{" + "key=" + getKey() + ",player=" + getPlayer().getUniqueId() + "}";
    }
}