package us.crazycrew.crazycore.paper.player;

import us.crazycrew.crazycore.player.CrazyPlayer;
import us.crazycrew.crazycore.player.CrazyPlayerRegistry;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Description: Handles all player information on Paper.
 */
public class PaperPlayerRegistry extends CrazyPlayerRegistry {

    /**
     * Registers a new paper player.
     *
     * @param player the player to register
     * @return the registered player
     */
    @Nullable
    public CrazyPlayer register(@NotNull Player player) {
        return register(new PaperPlayer(player));
    }

    /**
     * Unregisters an existing paper play if not null.
     *
     * @param player the player to unregister
     * @return the unregistered player
     */
    @Nullable
    public CrazyPlayer unregister(@NotNull Player player) {
        return get(player.getUniqueId()) == null ? null : unregister(get(player.getUniqueId()));
    }

    /**
     * Get a player if they exist.
     *
     * @param player the player to fetch
     * @return the fetched player
     */
    @Nullable
    public CrazyPlayer get(@NotNull Player player) {
        return get(player.getUniqueId());
    }
}