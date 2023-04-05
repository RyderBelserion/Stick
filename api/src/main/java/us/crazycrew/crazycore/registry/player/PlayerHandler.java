package us.crazycrew.crazycore.registry.player;

import org.jetbrains.annotations.Nullable;

/**
 * Description: A listener interface for when registering the events we require.
 */
public interface PlayerHandler {

    /**
     * Calls when a player joins the server.
     *
     * @param playerObject the player object
     */
    default void onJoin(@Nullable PlayerObject playerObject) {
        if (playerObject == null) return;
    }

    /**
     * Calls when a player quits the server.
     *
     * @param playerObject the player object
     */
    default void onQuit(@Nullable PlayerObject playerObject) {
        if (playerObject == null) return;
    }
}