package us.crazycrew.crazycore.senders;

import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;
import us.crazycrew.crazycore.registry.keys.Key;
import us.crazycrew.crazycore.utils.AdventureUtils;
import java.util.UUID;

/**
 * Description: An abstract class that represents the console sender on any platform.
 */
public abstract class Console extends Player {

    /**
     * Creates a console sender.
     */
    public Console() {
        super(Key.of(new UUID(0, 0)));
    }

    /**
     * Sends a message to console with Adventure API support.
     *
     * @param prefix true to insert command prefix
     * @param value prefix to include
     * @param message message to send
     */
    @Override
    public void send(boolean prefix, boolean hasItalics, String value, @NotNull ComponentLike message) {
        sendMessage(AdventureUtils.parse(value, hasItalics).append(message));
    }
}