package us.crazycrew.crazycore.registry.player;

import org.jetbrains.annotations.Nullable;
import us.crazycrew.crazycore.registry.KeyedRegistry;

import java.util.Locale;
import java.util.UUID;

/**
 * Description: Handles creating and fetching player objects.
 */
public abstract class PlayerRegistry extends KeyedRegistry<PlayerObject> {

    /**
     * Get the registered player by uuid.
     * <p>
     * Will return null if no player registered.
     *
     * @param uuid player uuid
     * @return registered player or null
     */
    @Nullable
    public PlayerObject get(UUID uuid) {
        return get(PlayerObject.createKey(uuid));
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
    public PlayerObject get(String name) {
        String lowerCaseName = name.toLowerCase(Locale.ROOT);

        for (PlayerObject playerObject : entries.values()) {
            if (playerObject.getName().toLowerCase(Locale.ROOT).equals(lowerCaseName)) return playerObject;
        }

        return null;
    }
}