package us.crazycrew.crazycore.player;

import us.crazycrew.crazycore.registry.keys.Key;
import us.crazycrew.crazycore.registry.senders.types.Sender;
import us.crazycrew.crazycore.utils.AdventureUtils;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

/**
 * Description: A player object represented by uuids in order to have multi platform support.
 */
public abstract class CrazyPlayer extends Sender {

    /**
     * Constructor to build a player.
     *
     * @param key unique identifier
     */
    public CrazyPlayer(@NotNull Key key) {
        super(key);
    }

    /**
     * Create a new key.
     *
     * @param uuid player uuid
     * @return a new key
     */
    public static Key createKey(@NotNull UUID uuid) {
        return Key.of(uuid);
    }

    /**
     * Get the player's name.
     *
     * @return player's name
     */
    @NotNull
    public abstract String getName();

    /**
     * Get the player's UUID.
     *
     * @return player's UUID
     */
    @NotNull
    public abstract UUID getUUID();

    @Override
    public void send(boolean hasPrefix, String prefix, @NotNull ComponentLike message) {
        sendMessage(hasPrefix ? AdventureUtils.parse(prefix).append(message) : message);
    }
}