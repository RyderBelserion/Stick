package us.crazycrew.crazycore.player;

import us.crazycrew.crazycore.registry.KeyedRegistry;
import org.jetbrains.annotations.Nullable;
import java.util.Locale;
import java.util.UUID;

/**
 * Description: Handles creating and fetching player objects.
 */
public abstract class CrazyPlayerRegistry extends KeyedRegistry<CrazyPlayer> {

    /**
     * Get the registered player by uuid.
     * <p>
     * Will return null if no player registered.
     *
     * @param uuid player uuid
     * @return registered player or null
     */
    @Nullable
    public CrazyPlayer get(UUID uuid) {
        return get(CrazyPlayer.createKey(uuid));
    }

    /**
     * Get the registered player by name.
     * <p>
     * Will return null if no player registered.
     *
     * @param name player name
     * @return registered player or null
     */
    @Nullable
    public CrazyPlayer get(String name) {
        String lowerCaseName = name.toLowerCase(Locale.ROOT);

        for (CrazyPlayer crazyPlayer : entries.values()) {
            if (crazyPlayer.getName().toLowerCase(Locale.ROOT).equals(lowerCaseName)) return crazyPlayer;
        }

        return null;
    }
}