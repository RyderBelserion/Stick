package us.crazycrew.crazycore.registry.senders.types;

import us.crazycrew.crazycore.registry.keys.Keyed;
import us.crazycrew.crazycore.registry.keys.Key;
import us.crazycrew.crazycore.utils.AdventureUtils;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

/**
 * Description: An abstract class that represents a player on any platform.
 */
public abstract class Sender extends Keyed implements Audience {

    /**
     * Constructor to build a sender
     *
     * @param key unique key
     */
    public Sender(@NotNull Key key) {
        super(key);
    }

    /**
     * Send a message with prefix.
     *
     * @param message message to send
     * @param prefix prefix to include
     * @param placeholders message placeholders
     */
    public void send(String prefix, String message, @NotNull TagResolver.Single... placeholders) {
        send(true, message, prefix, placeholders);
    }

    /**
     * Send a message with optional prefix.
     *
     * @param hasPrefix true to insert command prefix
     * @param prefix prefix to include
     * @param message message to send
     * @param placeholders message placeholders
     */
    public void send(boolean hasPrefix, String prefix, String message, @NotNull TagResolver.Single... placeholders) {
        if (message == null) return;

        for (String splitMessage : message.split("\n")) {
            send(hasPrefix, prefix, AdventureUtils.parse(splitMessage, placeholders));
        }
    }

    /**
     * Send a message with no prefix.
     *
     * @param message message to send
     */
    public void send(@NotNull ComponentLike message) {
        send(false, "", message);
    }

    /**
     * Send a message with a prefix.
     *
     * @param prefix prefix to include
     * @param message message to send
     */
    public void send(String prefix, @NotNull ComponentLike message) {
        send(true, prefix, message);
    }

    /**
     * Send a message.
     *
     * @param hasPrefix true to insert command prefix
     * @param prefix prefix to include
     * @param message message to send
     */
    public abstract void send(boolean hasPrefix, String prefix, @NotNull ComponentLike message);
}