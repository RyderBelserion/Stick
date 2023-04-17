package us.crazycrew.crazycore.registry.player;

import org.jetbrains.annotations.Nullable;
import us.crazycrew.crazycore.registry.keys.Key;
import us.crazycrew.crazycore.senders.Player;
import us.crazycrew.crazycore.utils.AdventureUtils;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;
import java.net.URL;
import java.util.UUID;

/**
 * Description: A player object represented by uuids in order to have multi-platform support.
 */
public abstract class PlayerObject extends Player {

    /**
     * Constructor to build a player.
     *
     * @param key unique identifier
     */
    public PlayerObject(@NotNull Key key) {
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
     * @return player's name
     */
    @NotNull
    public abstract String getName();

    /**
     * @return player's uuid
     */
    @NotNull
    public abstract UUID getUUID();

    /**
     * @return player's skin
     */
    @Nullable
    public abstract URL getSkin();

    /**
     * @return player's health
     */
    public abstract int getHealth();

    /**
     * @return player's ping
     */
    public abstract int getPing();

    @Override
    public void send(boolean hasPrefix, boolean hasItalics, String prefix, @NotNull ComponentLike message) {
        sendMessage(hasPrefix ? AdventureUtils.parse(prefix, hasItalics).append(message) : message);
    }
}