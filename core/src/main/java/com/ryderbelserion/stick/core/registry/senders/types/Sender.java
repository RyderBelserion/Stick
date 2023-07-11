package com.ryderbelserion.stick.core.registry.senders.types;

import com.ryderbelserion.stick.core.registry.keys.Key;
import com.ryderbelserion.stick.core.registry.keys.Keyed;
import com.ryderbelserion.stick.core.utils.AdventureUtils;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

public abstract class Sender extends Keyed implements Audience {

    public Sender(@NotNull Key key) {
        super(key);
    }

    public void send(String prefix, String message, @NotNull TagResolver.Single... placeholders) {
        send(true, message, prefix, placeholders);
    }

    public void send(boolean hasPrefix, String prefix, String message, @NotNull TagResolver.Single... placeholders) {
        if (message == null) return;

        for (String splitMessage : message.split("\n")) {
            send(hasPrefix, prefix, AdventureUtils.parse(splitMessage, placeholders));
        }
    }

    public void send(@NotNull ComponentLike message) {
        send(false, "", message);
    }

    public void send(String prefix, @NotNull ComponentLike message) {
        send(true, prefix, message);
    }

    public abstract void send(boolean hasPrefix, String prefix, @NotNull ComponentLike message);
}