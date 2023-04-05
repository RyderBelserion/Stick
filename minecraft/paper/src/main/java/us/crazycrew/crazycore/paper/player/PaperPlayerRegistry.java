package us.crazycrew.crazycore.paper.player;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.crazycrew.crazycore.registry.player.PlayerObject;
import us.crazycrew.crazycore.registry.player.PlayerRegistry;

/**
 * Description: Handles all player information on Paper.
 */
public class PaperPlayerRegistry extends PlayerRegistry {

    /**
     * Registers a new paper player.
     *
     * @param player the player to register
     * @return the registered player
     */
    @Nullable
    public PlayerObject register(@NotNull Player player) {
        return register(new PaperPlayerObject(player));
    }

    /**
     * Unregisters an existing paper play if not null.
     *
     * @param player the player to unregister
     * @return the unregistered player
     */
    @Nullable
    public PlayerObject unregister(@NotNull Player player) {
        return get(player.getUniqueId()) == null ? null : unregister(get(player.getUniqueId()));
    }

    /**
     * Get a player if they exist.
     *
     * @param player the player to fetch
     * @return the fetched player
     */
    @Nullable
    public PlayerObject get(@NotNull Player player) {
        return get(player.getUniqueId());
    }
}