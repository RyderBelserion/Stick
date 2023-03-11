package us.crazycrew.crazycore.player;

import org.jetbrains.annotations.Nullable;

/**
 * Description: A listener interface for when registering the events we require.
 */
public interface CrazyPlayerListener {

    /**
     * Calls when a player joins the server.
     *
     * @param crazyPlayer the player object
     */
    default void onJoin(@Nullable CrazyPlayer crazyPlayer) {
        if (crazyPlayer == null) return;
    }

    /**
     * Calls when a player quits the server.
     *
     * @param crazyPlayer the player object
     */
    default void onQuit(@Nullable CrazyPlayer crazyPlayer) {
        if (crazyPlayer == null) return;
    }
}